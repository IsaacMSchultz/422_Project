package whiteBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.HashSet;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.HalsteadDifficulty;
import StructuralMetrics.HalsteadLength;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class HalsteadDifficultyTest {

	Integer[] tokens = { TokenTypes.DEC, TokenTypes.INC, TokenTypes.LNOT, TokenTypes.POST_DEC,
			TokenTypes.POST_INC, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, TokenTypes.ASSIGN, TokenTypes.BAND,
			TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR,
			TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA,
			TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT,
			TokenTypes.LAND, TokenTypes.LE, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN,
			TokenTypes.MOD, TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,
			TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR,
			TokenTypes.QUESTION, TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT,
			TokenTypes.NUM_LONG };
	
	HashSet<Integer> expectedTokens = new HashSet<Integer>(Arrays.asList(tokens));

	@Test
	public void testGetDefaultTokens() {
		HalsteadDifficulty test = new HalsteadDifficulty();

		for (int item : test.getDefaultTokens())
			assertTrue(expectedTokens.contains(item));
	}

	@Test
	public void testGetAcceptableTokens() {
		HalsteadDifficulty test = new HalsteadDifficulty();

		for (int item : test.getAcceptableTokens())
			assertTrue(expectedTokens.contains(item));
	}

	@Test
	public void testGetRequiredTokens() {
		HalsteadDifficulty test = new HalsteadDifficulty();

		for (int item : test.getRequiredTokens())
			assertTrue(expectedTokens.contains(item));
	}
	
	@Test
	public void testVisit() {
		HalsteadDifficulty test = new HalsteadDifficulty();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		assertEquals(1.0, test.getUniqueOperators(), 0.1);
		assertEquals(1.0 , test.getUniqueOperands(), 0.1);
		assertEquals(1.0, test.getOperands(), 0.1);
	}

	@Mock
	HalsteadDifficulty tester = mock(HalsteadDifficulty.class);

	@Test
	public void testGetHalsteadDifficulty1() {
		HalsteadDifficulty test = spy(new HalsteadDifficulty());
		DetailAST ast = new DetailAST();

		doReturn(1.0).when(test).getOperands(); // operand
		doReturn(1.0).when(test).getUniqueOperators(); // operator
		doReturn(1.0).when(test).getUniqueOperands(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (1 / 2) * (1 / 1) = 1/2
		assertEquals(0.5, test.getHalsteadDifficulty(), 0.1);
	}
	
	@Test
	public void testGetHalsteadDifficulty2() {
		HalsteadDifficulty test = spy(new HalsteadDifficulty());
		DetailAST ast = new DetailAST();

		doReturn(20.0).when(test).getOperands(); // operand
		doReturn(1.0).when(test).getUniqueOperators(); // operator
		doReturn(1.0).when(test).getUniqueOperands(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (1 / 2) * (20 / 1) = 10
		assertEquals(10, test.getHalsteadDifficulty(), 0.1);
	}
	
	@Test
	public void testGetHalsteadDifficulty3() {
		HalsteadDifficulty test = spy(new HalsteadDifficulty());
		DetailAST ast = new DetailAST();

		doReturn(20.0).when(test).getOperands(); // operand
		doReturn(20.0).when(test).getUniqueOperators(); // operator
		doReturn(1.0).when(test).getUniqueOperands(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (20 / 2) * (20 / 1) = 200
		assertEquals(200, test.getHalsteadDifficulty(), 0.1);
	}
	
	@Test
	public void testGetHalsteadDifficulty4() {
		HalsteadDifficulty test = spy(new HalsteadDifficulty());
		DetailAST ast = new DetailAST();

		doReturn(37.0).when(test).getOperands(); // operand
		doReturn(15.0).when(test).getUniqueOperators(); // operator
		doReturn(11.0).when(test).getUniqueOperands(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (15 / 2) * (37 / 11) = 25.22727
		assertEquals(25.22727, test.getHalsteadDifficulty(), 0.1);
	}
}
