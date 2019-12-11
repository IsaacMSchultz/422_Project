package Deliverable3Tests.whiteBoxTests;

import StructuralMetrics.OperandCountCheck;
import StructuralMetrics.OperatorCountCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class OperatorCountTest {

	int[] expectedTokens = { TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT,
			TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR,
			TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV,
			TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC,
			TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT,
			TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD,
			TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC,
			TokenTypes.POST_INC, TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR,
			TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
			TokenTypes.UNARY_PLUS };

	@Test
	public void testGetDefaultTokens() {
		OperatorCountCheck test = new OperatorCountCheck();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		OperatorCountCheck test = new OperatorCountCheck();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		OperatorCountCheck test = new OperatorCountCheck();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	@Test
	public void testGetOperatorCount1() { // tests with a single token
		OperandCountCheck test = new OperandCountCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(expectedTokens[0]).when(ast).getType(); // operand
		doReturn("operand").when(ast).getText();
		test.visitToken(ast);

		assertEquals(1, test.getCount());
		assertEquals(1, test.getUniqueCount());
	}

	@Test
	public void testGetOperatorCount2() { // checks to see if it can distinguish unique operators
		OperandCountCheck test = new OperandCountCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(expectedTokens[0]).when(ast).getType(); // operator
		doReturn("operator").when(ast).getText();
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		assertEquals(20, test.getCount());
		assertEquals(1, test.getUniqueCount());
	}

	@Test
	public void testGetOperatorCount3() { // test ALL the operators!!!
		OperandCountCheck test = new OperandCountCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		int count = 0;
		for (int operator : expectedTokens) {
			doReturn(operator).when(ast).getType();
			doReturn("operator" + operator).when(ast).getText();

			for (int i = 0; i < 20; i++) { // do 20 operator
				test.visitToken(ast);
			}

			count++;
		}

		int finalNumber = count * 20;

		assertEquals(finalNumber, test.getCount());
		assertEquals(count, test.getUniqueCount());
	}
}
