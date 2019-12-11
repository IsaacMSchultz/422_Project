package Deliverable3Tests.whiteBoxTests;

import StructuralMetrics.OperandCountCheck;
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
public class OperandCountTest {

	int[] expectedTokens = { TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT,
			TokenTypes.NUM_LONG };

	@Test
	public void testGetDefaultTokens() {
		OperandCountCheck test = new OperandCountCheck();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		OperandCountCheck test = new OperandCountCheck();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		OperandCountCheck test = new OperandCountCheck();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	@Test
	public void testgetOperandsCount1() { //tests with a single token
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
	public void testgetOperandsCount2() { //tests unique vs count
		OperandCountCheck test = new OperandCountCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(expectedTokens[0]).when(ast).getType(); // operand
		doReturn("operand").when(ast).getText();
		for (int i = 0; i < 20; i++) { // do 20 operands
			test.visitToken(ast);
		}

		assertEquals(20, test.getCount());
		assertEquals(1, test.getUniqueCount());
	}

	@Test
	public void testgetOperandsCount3() { //this test tries every possible operand type we have included
		OperandCountCheck test = new OperandCountCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree
		
		// Now lets get some more unique operators and operands in there.

		doReturn(expectedTokens[0]).when(ast).getType(); // operand 1
		doReturn("operand1").when(ast).getText();
		for (int i = 0; i < 20; i++) { // do 20 operands
			test.visitToken(ast);
		}

		doReturn(expectedTokens[1]).when(ast).getType(); // operator 2
		doReturn("operand2").when(ast).getText();
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		doReturn(expectedTokens[2]).when(ast).getType(); // operand 3
		doReturn("operand3").when(ast).getText();
		test.visitToken(ast);

		doReturn(expectedTokens[3]).when(ast).getType(); // operand 4
		doReturn("operand4").when(ast).getText();
		test.visitToken(ast);

		assertEquals(42, test.getCount());
		assertEquals(4, test.getUniqueCount());
	}
}
