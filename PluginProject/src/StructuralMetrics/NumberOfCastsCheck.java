package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.*;

public class NumberOfCastsCheck extends AbstractCheck {

	int casts;

	@Override
	public void beginTree(DetailAST rootAST) {
		casts = 0;
	}

	@Override
	public void visitToken(DetailAST aAST) {
		casts++;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		log(rootAST, "Number of casts: {0}.", casts);
	}

	public int getCasts() {
		return casts;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.TYPECAST };
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.TYPECAST };
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
}
