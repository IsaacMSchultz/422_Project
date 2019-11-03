package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

//Use these to keep track of the tokens required for children.
import java.util.ArrayList;

public class HalsteadVocabulary extends AbstractCheck {

	private int halsteadVocabulary;

	private OperandCountCheck operandCount = new OperandCountCheck();
	private OperatorCountCheck operatorCount = new OperatorCountCheck();

	// Store the tokens they accept in a list so that they can be easily searched.
	private ArrayList<Integer> operandTokens = arrayToList(operandCount.getDefaultTokens());
	private ArrayList<Integer> operatorTokens = arrayToList(operatorCount.getDefaultTokens());

	@Override
	public void beginTree(DetailAST rootAST) {
		// Call the begin tree function of each check we depend on.
		operandCount.beginTree(rootAST);
		operatorCount.beginTree(rootAST);
	}

	@Override
	public void visitToken(DetailAST ast) {
		if (operandTokens.contains(ast.getType())) {
			operandCount.visitToken(ast);
		}
		if (operatorTokens.contains(ast.getType())) {
			operatorCount.visitToken(ast);
		}
	}

	// This is the function where the halstead volume gets calculated.
	@Override
	public void finishTree(DetailAST rootAST) {
		int uniqueOperands = getUniqueOperandCount();
		int uniqueOperators = getUniqueOperatorCount();

		halsteadVocabulary = uniqueOperands + uniqueOperators;

		try {
			log(0, "Halstead Vocabulary: " + halsteadVocabulary);
		} catch (NullPointerException e) {
			System.out.println("Can't run log unless called from treewalker!");
		}
	}

	// Public getter for the halstead length.
	public int getHalsteadVocabulary() {
		return halsteadVocabulary;
	}

	// getters for whitebox testing
	public int getUniqueOperandCount() {
		return operandCount.getUniqueCount();
	}

	public int getUniqueOperatorCount() {
		return operatorCount.getUniqueCount();
	}

	@Override
	public int[] getDefaultTokens() {
		return ArrayConcatenator.concatArray(operandCount.getDefaultTokens(), operatorCount.getDefaultTokens());
	}

	@Override
	public int[] getAcceptableTokens() {
		return ArrayConcatenator.concatArray(operandCount.getAcceptableTokens(), operatorCount.getAcceptableTokens());
	}

	@Override
	public final int[] getRequiredTokens() {
		return ArrayConcatenator.concatArray(operandCount.getRequiredTokens(), operatorCount.getRequiredTokens());
	}

	// Simple function to create an ArrayList from an integer array
	private ArrayList<Integer> arrayToList(int[] array) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for (int i : array) {
			returnList.add(i);
		}
		return returnList;
	}
}