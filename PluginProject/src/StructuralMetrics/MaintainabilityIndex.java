package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

import java.lang.Math;
import java.util.ArrayList;

public class MaintainabilityIndex extends AbstractCheck {

	double MaintainabilityIndex = 0;

	private CyclomaticComplexityCounter cyclomaticComplexity = new CyclomaticComplexityCounter();
	private HalsteadVolume halsteadVolume = new HalsteadVolume();

	// Store the tokens they accept in a list so that they can be easily searched.
	private ArrayList<Integer> cyclomaticComplexityTokens = arrayToList(cyclomaticComplexity.getDefaultTokens());
	private ArrayList<Integer> halsteadVolumeTokens = arrayToList(halsteadVolume.getDefaultTokens());

	@Override
	public void beginTree(DetailAST rootAST) {
		cyclomaticComplexity.beginTree(rootAST);
		halsteadVolume.beginTree(rootAST);
		MaintainabilityIndex = 0;
	}

	@Override
	public void visitToken(DetailAST ast) {
		if (cyclomaticComplexityTokens.contains(ast.getType())) {
			cyclomaticComplexity.visitToken(ast);
		}
		if (halsteadVolumeTokens.contains(ast.getType())) {
			halsteadVolume.visitToken(ast);
		}
	}

	@Override
	public void leaveToken(DetailAST ast) {
		if (cyclomaticComplexityTokens.contains(ast.getType())) {
			cyclomaticComplexity.leaveToken(ast);
		}
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		// Call the finish tree function on the checks we depend on
		halsteadVolume.finishTree(rootAST);

		int G = getCyclomaticComplexity();
		double V = getHalsteadVolume();
		int LOC = getLOC();

		MaintainabilityIndex = 171 - 5.2 * log2(V) - 0.23 * G - 16.2 * log2(LOC) + 50;

		try {
			log(0, "Maintainability index: " + Double.toString(MaintainabilityIndex));
		} catch (NullPointerException e) {
			System.out.println("Can't run log unless called from treewalker!");
		}

	}

	private int getCyclomaticComplexity() { //Using this function since trying to mock private fields' functions is really difficult.
		return cyclomaticComplexity.getCount();
	}

	private int getLOC() { //Using this function since trying to mock private fields' functions is really difficult.
		return this.getFileContents().getText().size();
	}

	public double getMaintainabilityIndex() {
		return MaintainabilityIndex;
	}

	// getter for whitebox testing
	public double getHalsteadVolume() {
		return halsteadVolume.getHalsteadVolume();
	}

	@Override
	public int[] getDefaultTokens() {
		return ArrayConcatenator.concatArray(cyclomaticComplexity.getDefaultTokens(),
				halsteadVolume.getDefaultTokens());
	}

	@Override
	public int[] getAcceptableTokens() {
		return ArrayConcatenator.concatArray(cyclomaticComplexity.getAcceptableTokens(),
				halsteadVolume.getAcceptableTokens());
	}

	@Override
	public final int[] getRequiredTokens() {
		return ArrayConcatenator.concatArray(cyclomaticComplexity.getRequiredTokens(),
				halsteadVolume.getRequiredTokens());
	}

	public static double log2(int x) {
		return (Math.log(x) / Math.log(2));
	}

	public static double log2(double x) {
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
