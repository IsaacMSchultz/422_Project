package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.*;
import java.util.*;

public class OperandCountCheck extends AbstractCheck {

	private static final String CATCH_MSG = "Operand Count: ";
	private static final String UNIQUE = "Unique Operands: ";
	
	private int operandCount;
	
	private Hashtable uniqueOperands;
	
	public int getOperandCount() {
		return this.operandCount;
	}
	
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG};	
	}

public void visitToken(DetailAST aAST) {
		if(inExpression(aAST)) {
			operandCount++;
		}
		boolean endFound = false;				
		while(!endFound) {
			DetailAST currentToken = aAST.getNextSibling();
			if(currentToken==null) {
				endFound = true;
			}
			else {
				if(currentToken.getType()==TokenTypes.IDENT) {
					if(!uniqueOperands.containsKey(currentToken.toString())){
						uniqueOperands.put(currentToken.toString(),1);
					}
				}
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
		this.uniqueOperands = new Hashtable();
	}
	
	@Override
	public void finishTree(DetailAST rootAST) {
		log(rootAST, CATCH_MSG + operandCount);
		log(rootAST, UNIQUE, + uniqueOperands.size());
	}

}
