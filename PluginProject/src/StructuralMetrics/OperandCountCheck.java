package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.*;

public class OperandCountCheck extends AbstractCheck {
	int operandCount = 0; // Storing the number of operators in the current file.

	@Override
	public void beginTree(DetailAST rootAST) {
		operandCount = 0; // Reset to 0 when we start a new tree.
	}

	@Override
	public void visitToken(DetailAST aAST) {
		operandCount++;
	}
	
	// Public getter for the operand count
	public int getCount() {
		return operandCount;
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT,
				TokenTypes.NUM_LONG };
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return null;
	}
}
