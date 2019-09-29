package StructuralMetrics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

class OperandCountCheckTest {

	OperandCountCheck occ = new OperandCountCheck();
	int[] expectedArray = {TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG};
	
	@Mock 
	DetailAST detailASTMock;
	
	@Mock
	OperandCountCheck occMock;
	
	
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
	void testGetOperandCount() {

		Assert.assertEquals(0, occ.getOperandCount());
	}

	@Test
	void testVisitTokenDetailAST() {
		when(detailASTMock.getParent().getType()).thenReturn(TokenTypes.EXPR);
		when(detailASTMock.toString()).thenReturn("2");
		when(detailASTMock.getType()).thenReturn(TokenTypes.NUM_INT);
		occMock.visitToken(detailASTMock);
		verify(occMock).visitToken(detailASTMock);
		Assert.assertEquals(1, occ.getOperandCount());
		Assert.assertEquals(1, occ.getUniqueOperandCount());
	}

	@Test
	void testBeginTreeDetailAST() {
		Assert.assertEquals(0, occ.getOperandCount());
		Assert.assertEquals(0, occ.getUniqueOperandCount());
	}

	@Test
	void testFinishTreeDetailAST() {
		Assert.assertEquals(0, occ.getOperandCount());
		Assert.assertEquals(0, occ.getUniqueOperandCount());
	}

}
