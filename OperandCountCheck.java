package HalsteadCheckPackage;

import com.puppycrawl.tools.checkstyle.api.*;

public class OperandCountCheck extends AbstractCheck {

	int operandCount = 0;
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG};
	}

public void visitToken(DetailAST aAST) {
		
		operandCount++;
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return null;
	}

}
