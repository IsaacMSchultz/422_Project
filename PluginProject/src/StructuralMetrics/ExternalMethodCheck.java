package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.*;

public class ExternalMethodCheck extends AbstractCheck{

	
	private int extMethodCount;
	private final String CATCH_MSG = "External method count: ";
	
	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.METHOD_CALL};
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.METHOD_CALL};
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return new int[0];
	}
	
	public void visitToken(DetailAST aAST) {
		if(aAST.findFirstToken(TokenTypes.DOT) != null) {
			extMethodCount++;	
		}
	}
	
	public void beginTree(DetailAST rootAST) {
		extMethodCount = 0;
	}
	
	public void finishTree(DetailAST rootAST) {
		log(rootAST, CATCH_MSG + extMethodCount);
	}

}
