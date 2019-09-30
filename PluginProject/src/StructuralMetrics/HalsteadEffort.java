package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

import java.util.ArrayList;

public class HalsteadEffort extends AbstractCheck {

	private double halsteadEffort;
	
	// I AM IN THE PROCESS OF TURNING OPERAND INTO HALSTEAD VOLUME AND OPERATOR INTO HALSTEAD VOCABULARY!!!!!!

	private HalsteadDifficulty halsteadDifficulty = new HalsteadDifficulty();
	private HalsteadVolume halsteadVolume = new HalsteadVolume();

	// Store the tokens they accept in a list so that they can be easily searched.
	private ArrayList<Integer> operandTokens = arrayToList(halsteadDifficulty.getDefaultTokens());
	private ArrayList<Integer> operatorTokens = arrayToList(halsteadVolume.getDefaultTokens());

	@Override
	public void beginTree(DetailAST rootAST) {
		// Call the begin tree function of each check we depend on.
		halsteadDifficulty.beginTree(rootAST);
		halsteadVolume.beginTree(rootAST);
	}

	@Override
	public void visitToken(DetailAST ast) {
		if (operandTokens.contains(ast.getType())) {
			halsteadDifficulty.visitToken(ast);
		}
		if (operatorTokens.contains(ast.getType())) {
			halsteadVolume.visitToken(ast);
		}
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		// Call the begin tree function of each check we depend on.
		halsteadDifficulty.finishTree(rootAST);
		halsteadVolume.finishTree(rootAST);

		int difficulty = halsteadDifficulty.getHalsteadDifficulty();
		double volume = halsteadVolume.getHalsteadVolume();

		halsteadEffort = difficulty * volume;
		
		try {
			log(0, "Halstead Effort: " + halsteadEffort);
		}
		catch (NullPointerException e) {
			System.out.println("Can't run log unless called from treewalker!");
		}
	}

	// Public getter for the halstead volume.
	public double getHalsteadEffort() {
		return halsteadEffort;
	}

	@Override
	public int[] getDefaultTokens() {
		System.out.println("1: " + halsteadDifficulty.getDefaultTokens().length + ", 2: " + halsteadVolume.getDefaultTokens().length + ", 3: " + ArrayConcatenator.concatArray(halsteadDifficulty.getDefaultTokens(), halsteadVolume.getDefaultTokens()).length);
		return ArrayConcatenator.concatArray(halsteadDifficulty.getDefaultTokens(), halsteadVolume.getDefaultTokens());
	}

	@Override
	public int[] getAcceptableTokens() {
		return ArrayConcatenator.concatArray(halsteadDifficulty.getAcceptableTokens(), halsteadVolume.getAcceptableTokens());
	}

	@Override
	public final int[] getRequiredTokens() {
		return ArrayConcatenator.concatArray(halsteadDifficulty.getRequiredTokens(), halsteadVolume.getRequiredTokens());
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