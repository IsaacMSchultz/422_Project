package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.OperandCountCheck;

class OperandCountCheckTest {

	OperandCountCheck occ = new OperandCountCheck();
	int[] expectedArray = {TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG};
	
	@Mock 
	DetailAST detailASTMock;
	
	@Mock
	OperandCountCheck occMock;
	
	
	@Test
	void testGetDefaultTokens() {

		assertArrayEquals(expectedArray, occ.getDefaultTokens() );
	}

	@Test
	void testGetAcceptableTokens() {

		assertArrayEquals(expectedArray, occ.getAcceptableTokens());
	}

	@Test
	void testGetRequiredTokens() {
		int[] emptyArray = new int[0]; 
		assertArrayEquals(emptyArray, occ.getRequiredTokens());
	}

	@Test
	void testGetOperandCount() {

		assertEquals(0, occ.getOperandCount());
	}

	@Test
	void testVisitTokenDetailAST() {
		when(detailASTMock.getParent().getType()).thenReturn(TokenTypes.EXPR);
		when(detailASTMock.toString()).thenReturn("2");
		when(detailASTMock.getType()).thenReturn(TokenTypes.NUM_INT);
		occMock.visitToken(detailASTMock);
		verify(occMock).visitToken(detailASTMock);
		assertEquals(1, occ.getOperandCount());
		assertEquals(1, occ.getUniqueOperandCount());
	}

	@Test
	void testBeginTreeDetailAST() {
		assertEquals(0, occ.getOperandCount());
		assertEquals(0, occ.getUniqueOperandCount());
	}

	@Test
	void testFinishTreeDetailAST() {
		assertEquals(0, occ.getOperandCount());
		assertEquals(0, occ.getUniqueOperandCount());
	}

}
