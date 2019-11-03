package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.*;

import java.util.HashSet;

public class OperandCountCheck extends AbstractCheck {
	int operandCount = 0; // Storing the number of operators in the current file.
	HashSet<String> uniqueOperands = new HashSet<String>(); // Keep a hashset of each operand

	@Override
	public void beginTree(DetailAST rootAST) {
		operandCount = 0; // Reset to 0 when we start a new tree.
		uniqueOperands = new HashSet<String>(); // Reset the operands we are counting when we restart!
	}

	@Override
	public void visitToken(DetailAST aAST) {
		operandCount++;

//		System.out.println(aAST.getText());
		uniqueOperands.add(aAST.getText()); // Assuming the text of the operand is what makes it unique!
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		log(0, "There are {0} unique operands that appear {1} times.", uniqueOperands.size(), operandCount);
	}

	// Public getter for the operand count
	public int getCount() {
		return operandCount;
	}

	// Public getter for the operand count
	public int getUniqueCount() {
		return uniqueOperands.size();
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT,
				TokenTypes.NUM_LONG };
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return new int[0];
	}
}
