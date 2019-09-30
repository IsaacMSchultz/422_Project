package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.*;

public class ExpressionCountCheck extends AbstractCheck {

	private int expressionCount;
	private final String CATCH_MSG = "The number of expressions: ";
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.EXPR}; 
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.EXPR}; 
	}
  
	//increments the expression count
	@Override
	public void visitToken(DetailAST aAST) {
		++expressionCount;
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	//calls begin tree to initialize count variable
	public void beginTree(DetailAST rootAST) {
		expressionCount = 0;
	}
	
	//logs the expression count
	public void finishTree(DetailAST rootAST) {
		log(rootAST, CATCH_MSG + expressionCount);
	}
	
	//returns the expression count 
	public int getCount() {
		return expressionCount;
	}

}
