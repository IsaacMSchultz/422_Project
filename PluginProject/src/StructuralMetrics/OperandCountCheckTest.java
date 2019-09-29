package StructuralMetrics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

class OperandCountCheckTest {

	OperandCountCheck occ = new OperandCountCheck();
	int[] expectedArray = {TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG};
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
		fail("Not yet implemented");
	}

	@Test
	void testBeginTreeDetailAST() {
		fail("Not yet implemented");
	}

	@Test
	void testFinishTreeDetailAST() {
		fail("Not yet implemented");
	}

}
