package test;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.HalsteadEffort;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class HalsteadEffortTest {

	int[] expectedTokens = { TokenTypes.DEC, TokenTypes.INC, TokenTypes.LNOT, TokenTypes.POST_DEC, TokenTypes.POST_INC,
			TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
			TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN,
			TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DIV,
			TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.LAND,
			TokenTypes.LE, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD,
			TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.SL,
			TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.QUESTION,
			TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG };

	@Test
	public void testGetDefaultTokens() {
		HalsteadEffort test = new HalsteadEffort();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		HalsteadEffort test = new HalsteadEffort();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		HalsteadEffort test = new HalsteadEffort();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	//This is the function that we will be doing all of our tests from, since all the others require mocking private fields thta we have not yet learned how to do.
	// AAA = Arrange, Act, Assert
	@Test
	public void testGetHalsteadEffort() {
		HalsteadEffort test = new HalsteadEffort();
		DetailAST ast = PowerMockito.mock(DetailAST.class);
		
		test.beginTree(ast); //begin the tree
		
		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); //operand		
		test.visitToken(ast);
		
		doReturn(TokenTypes.LNOT).when(ast).getType(); //operator	
		test.visitToken(ast);
		
		test.finishTree(ast);
		
		assertEquals(3, test.getHalsteadEffort(), 0.1);
		
	}

}
