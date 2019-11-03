package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

import java.util.ArrayList;

public class HalsteadVolume extends AbstractCheck {

	private double halsteadVolume;
	
	// I AM IN THE PROCESS OF TURNING OPERAND INTO HALSTEAD VOLUME AND OPERATOR INTO HALSTEAD VOCABULARY!!!!!!

	private HalsteadLength halsteadLength = new HalsteadLength();
	private HalsteadVocabulary halsteadVocabulary = new HalsteadVocabulary();

	// Store the tokens they accept in a list so that they can be easily searched.
	private ArrayList<Integer> operandTokens = arrayToList(halsteadLength.getDefaultTokens());
	private ArrayList<Integer> operatorTokens = arrayToList(halsteadVocabulary.getDefaultTokens());

	@Override
	public void beginTree(DetailAST rootAST) {
		// Call the begin tree function of each check we depend on.
		halsteadLength.beginTree(rootAST);
		halsteadVocabulary.beginTree(rootAST);
	}

	@Override
	public void visitToken(DetailAST ast) {
		if (operandTokens.contains(ast.getType())) {
			halsteadLength.visitToken(ast);
		}
		if (operatorTokens.contains(ast.getType())) {
			halsteadVocabulary.visitToken(ast);
		}
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		// Call the begin tree function of each check we depend on.
		halsteadLength.finishTree(rootAST);
		halsteadVocabulary.finishTree(rootAST);

		int length = getHalsteadLength();
		int vocabulary = getHalsteadVocabulary();

		System.out.println("length: " + length + " vocab: " + vocabulary);
		halsteadVolume = length * log2(vocabulary);
		
		try {
			log(0, "Halstead Volume: " + halsteadVolume);
		}
		catch (NullPointerException e) {
			System.out.println("Can't run log unless called from treewalker!");
		}
	}

	// Public getter for the halstead volume.
	public double getHalsteadVolume() {
		return halsteadVolume;
	}
	
	// getters for whitebox testing
	public int getHalsteadLength() {
		return halsteadLength.getHalsteadLength();
	}

	public int getHalsteadVocabulary() {
		return halsteadVocabulary.getHalsteadVocabulary();
	}


	@Override
	public int[] getDefaultTokens() {
		return ArrayConcatenator.concatArray(halsteadLength.getDefaultTokens(), halsteadVocabulary.getDefaultTokens());
	}

	@Override
	public int[] getAcceptableTokens() {
		return ArrayConcatenator.concatArray(halsteadLength.getAcceptableTokens(), halsteadVocabulary.getAcceptableTokens());
	}

	@Override
	public final int[] getRequiredTokens() {
		return ArrayConcatenator.concatArray(halsteadLength.getRequiredTokens(), halsteadVocabulary.getRequiredTokens());
	}
	
	public static double log2(int x) {
		return (Math.log(x) / Math.log(2));
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