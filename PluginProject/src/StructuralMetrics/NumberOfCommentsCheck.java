package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberOfCommentsCheck extends AbstractCheck {

	private int max = 30;
	int countComments;

	// Implementation of shotgun surgery, multiple methods with similar
	// functionality.
	// If a change needs to be made, all methods would need to be changed.
	public void ShotgunSurgeryPrint() {
		this.shotgunSurgery1();
		this.shotgunSurgery2();
		this.shotgunSurgery3();
		this.shotgunSurgery4();
		this.shotgunSurgery5();
	}

	public void shotgunSurgery1() {
		String a = "H";
		System.out.print(a);
	}

	public void shotgunSurgery2() {
		String a = "e";
		System.out.print(a);
	}

	public void shotgunSurgery3() {
		String a = "l";
		System.out.print(a);
	}

	public void shotgunSurgery4() {
		String a = "l";
		System.out.print(a);
	}

	public void shotgunSurgery5() {
		String a = "0";
		System.out.print(a);
	}

	public int getCount() {
		return this.countComments;
	}

	public int getMax() {

		return this.max;
	}

	public void setMax(int limit) {
		max = limit;
	}

	public void beginTree(DetailAST rootAST) {
		countComments = 0;
	}

	public void finishTree(DetailAST rootAST) {
		try {
			log(0, "Number of comments: {0}. Max number of comments exceeded", countComments);
		} catch (NullPointerException e) {
			System.out.println("Can't run log unless called from treewalker!");
		}
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.COMMENT_CONTENT };
	}

	@Override
	public boolean isCommentNodesRequired() {
		return true;
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.COMMENT_CONTENT };
	}

	@Override
	public void visitToken(DetailAST ast) {
		countComments++;
	}
}
