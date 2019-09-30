package StructuralMetrics;


import com.puppycrawl.tools.checkstyle.api.*;

public class LocalMethodCheck extends AbstractCheck{

	private int localMethodCount;
	private final String CATCH_MSG = "Local method count: ";
	
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.METHOD_CALL};
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.METHOD_CALL};
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	public void visitToken(DetailAST aAST) {
		if(aAST.findFirstToken(TokenTypes.DOT) == null) {
			localMethodCount++;	
		}
	}
	
	public void beginTree(DetailAST rootAST) {
		localMethodCount = 0;
	}
	
	public void finishTree(DetailAST rootAST) {
		log(rootAST, CATCH_MSG + localMethodCount);
	}

}
