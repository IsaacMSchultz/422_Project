package testPlugin;

import com.puppycrawl.tools.checkstyle.api.*;

public class ExpressionCountCheck extends AbstractCheck {

	private int expressionCount;
	private final String CATCH_MSG = "The number of expressions: ";
	
	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.EXPR}; 
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.EXPR}; 
	}
	
	@Override
	public void visitToken(DetailAST aAST) {
		++expressionCount;
		
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return new int[0];
	}
	
	public void beginTree(DetailAST rootAST) {
		expressionCount = 0;
	}
	
	public void finishTree(DetailAST rootAST) {
		log(rootAST, CATCH_MSG + expressionCount);
	}

}
