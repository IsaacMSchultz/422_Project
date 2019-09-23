package HalsteadCheckPackage;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadLengthCheck extends AbstractCheck {

	//private int[] operators = {TokenTypes.DEC, TokenTypes.INC, TokenTypes.LNOT, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS,TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.SL,TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR,TokenTypes.QUESTION};
	//private int[] operands = {TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG};
	private static final String CATCH_MSG = "HALSTEAD CHECK: ";
	
	//Halstead Length is the total number of operators and operands
	int halsteadLength = 0;
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.DEC, TokenTypes.INC, TokenTypes.LNOT, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS,TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.SL,TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR,TokenTypes.QUESTION, TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG};
	}

	@Override
	public void visitToken(DetailAST aAST) {
		//String operatorName = findVariableOperator(aAST);
		//reportStyleError(aAST, operatorName);
		halsteadLength++;
	}
	
	private String findVariableOperator(DetailAST aAST) {
		DetailAST identifier = aAST.findFirstToken(TokenTypes.IDENT);
		return identifier.toString();
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
	
	private void reportStyleError(DetailAST aAST, String operatorName) {
		log(aAST.getLineNo(), CATCH_MSG + operatorName);
		}

}
