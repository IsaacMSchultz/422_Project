package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberOfLoopsCheck extends AbstractCheck {

	private int max = 1;
	int loopCount;

	public void beginTree(DetailAST rootAST) {
		loopCount = 0;
	}

	public void finishTree(DetailAST rootAST) {
		log(0, "Number of loops: {0}. Max number of loops exceeded", loopCount);
	}

	@Override
	public int[] getAcceptableTokens() {
		return getRequiredTokens();
	}

	@Override
	public boolean isCommentNodesRequired() {
		return true;
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO, };
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO };
	}

	public void setMax(int limit) {
		max = limit;
	}

	@Override
	public void visitToken(DetailAST ast) {

		switch (ast.getType()) {
		case TokenTypes.LITERAL_FOR:
			loopCount++;
			break;

		case TokenTypes.LITERAL_WHILE:
			loopCount++;
			break;

		case TokenTypes.LITERAL_DO:
			loopCount++;
			break;
		}
	}

	public int getLoopCount() {
		// TODO Auto-generated method stub
		return loopCount;
	}
}