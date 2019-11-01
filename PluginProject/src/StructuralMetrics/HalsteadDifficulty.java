/*
	HalsteadDifficulty.java
	Developed by Isaac Schultz

	This file is responsible for calculating the halstead difficulty of a given code file. 
	This check makes use of both the operandCount and operatorCount checks, calling their visitToken
		functions, along with finishTree and beginTree.
	
	This class was purposely developed with the Cut-and-paste programming Anti-Pattern to show that testing
		can still be done properly even with poor code quality.
*/

package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

//Use these to keep track of the tokens required for children.
import java.util.ArrayList;

public class HalsteadDifficulty extends AbstractCheck {

	private int halsteadDifficulty;

	private OperandCounter operandCount = new OperandCounter(); //creating the checks that will get run within this check
	private OperatorCounter operatorCount = new OperatorCounter();

	// Store the tokens they accept in a list so that they can be easily searched.
	private ArrayList<Integer> operandTokens = arrayToList(operandCount.getDefaultTokens());
	private ArrayList<Integer> operatorTokens = arrayToList(operatorCount.getDefaultTokens());

	@Override
	public void beginTree(DetailAST rootAST) {
		operandCount.beginTree(rootAST); // Call the begin tree function of each check we depend on.
		operatorCount.beginTree(rootAST);
	}

	@Override
	public void visitToken(DetailAST ast) {
		if (operandTokens.contains(ast.getType())) { //call operandCount visitToken if the ast is an operand
			operandCount.visitToken(ast);
		}
		if (operatorTokens.contains(ast.getType())) { //call operatorCount visitToken if the ast is an operator
			operatorCount.visitToken(ast);
		}
	}

	// This is the function where the halstead volume gets calculated.
	@Override
	public void finishTree(DetailAST rootAST) {
		int uniqueOperators = getUniqueOperators(); // using getters to make whitebox testing easier
		int uniqueOperands = getUniqueOperands();
		int operands = getOperands();

		// formula for calculating halstead difficulty
		halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands); 

		try { // try-catch log since it can only be called from a treewalker.
			log(0, "Halstead Difficulty: " + halsteadDifficulty);
		} catch (NullPointerException e) {
			System.out.println("Can't run log unless called from treewalker!");
		}
	}

	// Public getter for the halstead length.
	public int getHalsteadDifficulty() {
		return halsteadDifficulty;
	}

	// getters for whitebox testing
	public int getUniqueOperators() {
		return operatorCount.getUniqueCount();
	}

	public int getUniqueOperands() {
		return operandCount.getUniqueCount();
	}

	public int getOperands() {
		return operandCount.getCount();
	}

	//token types from checks that are depending on
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

	// Simple function to create an ArrayList from an integer array. Part of cut-and-paste antipattern
	private ArrayList<Integer> arrayToList(int[] array) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for (int i : array) {
			returnList.add(i);
		}
		return returnList;
	}
}