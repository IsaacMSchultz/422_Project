package StructuralMetrics;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

class OperatorCountCheckTest {
	OperatorCountCheck  occ = new OperatorCountCheck();
	int[] expectedArray = {TokenTypes.DEC, TokenTypes.INC, TokenTypes.LNOT, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS,TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.SL,TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR,TokenTypes.QUESTION};
	
	@Mock 
	DetailAST detailASTMock;
	
	@Mock
	OperatorCountCheck occMock;
	
	@Test
	void testGetDefaultTokens() {
		Assert.assertArrayEquals(expectedArray, occ.getDefaultTokens() );
	}

	@Test
	void testGetAcceptableTokens() {
		Assert.assertArrayEquals(expectedArray, occ.getAcceptableTokens());
	}

	@Test
	void testGetRequiredTokens() {
		int[] emptyArray = new int[0]; 
		Assert.assertArrayEquals(emptyArray, occ.getRequiredTokens());
	}

	@Test
	void testGetOperatorCount() {
		Assert.assertEquals(0, occ.getOperatorCount());
	}

	@Test
	void testGetUniqueOperatorCount() {
		Assert.assertEquals(0, occ.getUniqueOperatorCount());
	}

	@Test
	void testVisitTokenDetailAST() {
		when(detailASTMock.getParent().getType()).thenReturn(TokenTypes.EXPR);
		when(detailASTMock.toString()).thenReturn("=");
		when(detailASTMock.getType()).thenReturn(TokenTypes.ASSIGN);
		occMock.visitToken(detailASTMock);
		verify(occMock).visitToken(detailASTMock);
		Assert.assertEquals(1, occ.getOperatorCount());
		Assert.assertEquals(1, occ.getUniqueOperatorCount());
	}

	@Test
	void testBeginTreeDetailAST() {
		Assert.assertEquals(0, occ.getOperatorCount());
		Assert.assertEquals(0, occ.getUniqueOperatorCount());
	}

	 
	@Test
	void testFinishTreeDetailAST() {

		Assert.assertEquals(0, occ.getOperatorCount());
		Assert.assertEquals(0, occ.getUniqueOperatorCount());
	}

}
