package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.*;
import java.util.*;

public class OperandCountCheck extends AbstractCheck {

	private static final String CATCH_MSG = "Operand Count: ";
	private static final String UNIQUE = "Unique Operands: ";
	
	private int operandCount = 0;
	private int nonVariableOperandCount = 0 ;
	
	private HashSet variableOperands = new HashSet();
	
	public int getOperandCount() {
		return this.operandCount;
	}
	
	public int getUniqueOperandCount() {
		if(this.variableOperands.isEmpty()) {
			return nonVariableOperandCount;
		}
		else return this.variableOperands.size()+nonVariableOperandCount;
	}
	
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG};	
	}

	public void visitToken(DetailAST aAST) {
		if(inExpression(aAST)) {
			operandCount++;
			if(aAST.getType()==TokenTypes.IDENT) {
				if(!variableOperands.contains(aAST.toString())){
					variableOperands.add(aAST.toString());
				}
				else nonVariableOperandCount++;		
			}
		}		
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG};
	}
	
	private boolean inExpression(DetailAST aAST) {
        return aAST.getParent().getType() == TokenTypes.EXPR;
    }

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	@Override
	public void beginTree(DetailAST rootAST) {
		operandCount = 0;
		this.variableOperands = new HashSet();
	}
	
	@Override
	public void finishTree(DetailAST rootAST) {
		log(rootAST, CATCH_MSG + operandCount);
		log(rootAST, UNIQUE, + this.getUniqueOperandCount());
	}

}
