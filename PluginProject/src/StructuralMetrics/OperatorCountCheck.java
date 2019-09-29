package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.*;
import java.util.*;

public class OperatorCountCheck extends AbstractCheck{

	private static final String CATCH_MSG = "Operator Count: ";
	private static final String UNIQUE_COUNT = "Unique operators: ";
	
	private int operatorCount;
	private Hashtable uniqueOperators;
	
	public int getOperatorCount() {
		return this.operatorCount;
	}
	
	public int getUniqueOperatorCount() {
		return this.uniqueOperators.size();
	}
	
	@Override
	public int[] getDefaultTokens() {
		
		return new int[] {TokenTypes.DEC, TokenTypes.INC, TokenTypes.LNOT, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS,TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.SL,TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR,TokenTypes.QUESTION};
	}
	
	public void visitToken(DetailAST aAST) {
		if(inExpression(aAST)) {
			operatorCount++;
			if(!uniqueOperators.containsKey(aAST.toString())){
				uniqueOperators.put(aAST.toString(),1);
			}
		}
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.DEC, TokenTypes.INC, TokenTypes.LNOT, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS,TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.SL,TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR,TokenTypes.QUESTION};
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	private boolean inExpression(DetailAST aAST) {
        return aAST.getParent().getType() == TokenTypes.EXPR;
    }
	
	@Override
	public void beginTree(DetailAST rootAST) {
		operatorCount = 0;
	}
	
	@Override
	public void finishTree(DetailAST rootAST) {
		log(rootAST, CATCH_MSG + operatorCount);
		log(rootAST, UNIQUE_COUNT, + getUniqueOperatorCount());
	}

}
