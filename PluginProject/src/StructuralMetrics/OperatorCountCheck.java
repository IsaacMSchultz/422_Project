package StructuralMetrics;

import java.util.HashSet;

import com.puppycrawl.tools.checkstyle.api.*;

public class OperatorCountCheck extends AbstractCheck {
	
	int[] toks = { TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR,
			TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN,
			TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT,
			TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND,
			TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT,
			TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL,
			TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION,
			TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR,
			TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS };
	
	int operatorCount = 0;
	HashSet<String> uniqueOperators = new HashSet<String>(); // Keep a hashset of each operator

	@Override
	public void beginTree(DetailAST rootAST) {
		operatorCount = 0; // Reset to 0 when we start a new tree.
		uniqueOperators = new HashSet<String>();
	}

	public void visitToken(DetailAST aAST) {
		operatorCount++;
		uniqueOperators.add(aAST.getText()); // Assuming the text of the operand is what makes it unique!
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		try {
			log(0, "There are {0} unique operators that appear {1} times.", uniqueOperators.size(), operatorCount);
		} catch (NullPointerException e) {
			System.out.println("Can't run log unless called from treewalker!");
		}
	}

	public int getCount() {
		return operatorCount;
	}

	public int getUniqueCount() {
		return uniqueOperators.size();
	}

	@Override
	public int[] getDefaultTokens() {
		return toks;
	}

	@Override
	public int[] getAcceptableTokens() {
		return getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}
}
