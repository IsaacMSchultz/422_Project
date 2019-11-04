/*
	HalsteadEffort.java
	Developed by Isaac Schultz

	This file is responsible for calculating the halstead effort of a given code file. 
	This check makes use of both the halsteadDifficulty and halsteadVolume checks, calling their visitToken
		functions, along with finishTree and beginTree.
	
	This class was purposely developed with the Cut-and-paste programming Anti-Pattern to show that testing
		can still be done properly even with poor code quality.
*/

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
	private ArrayList<Integer> difficultyTokens = arrayToList(halsteadDifficulty.getDefaultTokens());
	private ArrayList<Integer> volumeTokens = arrayToList(halsteadVolume.getDefaultTokens());

	@Override
	public void beginTree(DetailAST rootAST) {
		// Call the begin tree function of each check we depend on.
		halsteadDifficulty.beginTree(rootAST);
		halsteadVolume.beginTree(rootAST);
	}

	// call the visitToken of halsteadDifficulty and halsteadVolume
	@Override
	public void visitToken(DetailAST ast) {
		if (difficultyTokens.contains(ast.getType())) { //call halsteadDifficulty visitToken if the ast is a token that it needs
			halsteadDifficulty.visitToken(ast);
		}
		if (volumeTokens.contains(ast.getType())) { //call halsteadVolume visitToken if the ast is a token that it needs
			halsteadVolume.visitToken(ast);
		}
	}

	//This is the function where the Halstead Effort is calculated
	@Override
	public void finishTree(DetailAST rootAST) {
		// Call the begin tree function of each check we depend on.
		halsteadDifficulty.finishTree(rootAST);
		halsteadVolume.finishTree(rootAST);

		double difficulty = getHalsteadDifficulty(); // using getters to make whitebox testing easier
		double volume = getHalsteadVolume();

		halsteadEffort = difficulty * volume; //calculate halsteadEffort

		try { // try-catch log since it can only be called from a treewalker.
			log(0, "Halstead Effort: " + halsteadEffort);
		} catch (NullPointerException e) {
			System.out.println("Can't run log unless called from treewalker!");
		}
	}

	// Public getter for the halstead volume.
	public double getHalsteadEffort() {
		return halsteadEffort;
	}

	// getters for whitebox testing
	public double getHalsteadDifficulty() {
		return halsteadDifficulty.getHalsteadDifficulty();
	}

	public double getHalsteadVolume() {
		return halsteadVolume.getHalsteadVolume();
	}

	//token types from checks that are depending on
	@Override
	public int[] getDefaultTokens() {
		return ArrayConcatenator.concatArray(halsteadDifficulty.getDefaultTokens(), halsteadVolume.getDefaultTokens());
	}

	@Override
	public int[] getAcceptableTokens() {
		return ArrayConcatenator.concatArray(halsteadDifficulty.getAcceptableTokens(),
				halsteadVolume.getAcceptableTokens());
	}

	@Override
	public final int[] getRequiredTokens() {
		return ArrayConcatenator.concatArray(halsteadDifficulty.getRequiredTokens(),
				halsteadVolume.getRequiredTokens());
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