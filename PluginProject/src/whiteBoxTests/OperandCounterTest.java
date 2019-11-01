package whiteBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import StructuralMetrics.OperandCounter;
import StructuralMetrics.OperandCounter;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class OperandCounterTest {

	int[] expectedTokens = { TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT,
			TokenTypes.NUM_LONG };

	@Test
	public void testGetDefaultTokens() {
		OperandCounter test = new OperandCounter();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		OperandCounter test = new OperandCounter();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		OperandCounter test = new OperandCounter();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	// This is the function that we will be doing all of our tests from, since all
	// the others require mocking private fields thta we have not yet learned how to
	// do.
	// AAA = Arrange, Act, Assert
	@Test
	public void testGetOperandCount1() {
		OperandCounter test = new OperandCounter();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		assertEquals(1, test.getCount());
		assertEquals(1, test.getUniqueCount());
	}

	@Test
	public void testGetOperandCount2() {
		OperandCounter test = new OperandCounter();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		for (int i = 0; i < 20; i++) { // do 20 operands
			test.visitToken(ast);
			System.out.println("DSJKIOFDSKJFKLJDSFH");
		}

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		assertEquals(20, test.getCount());
		assertEquals(1, test.getUniqueCount());
	}

	@Test
	public void testGetOperandCount3() {
		OperandCounter test = new OperandCounter();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand

		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		assertEquals(20, test.getCount());
		assertEquals(1, test.getUniqueCount());
	}

	@Test
	public void testGetOperandCount4() {
		OperandCounter test = new OperandCounter();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand 1
		for (int i = 0; i < 20; i++) { // do 20 operands
			test.visitToken(ast);
		}

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator 1
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		// Now lets get some more unique operators and operands in there.

		doReturn(TokenTypes.IDENT).when(ast).getType(); // operand 2
		test.visitToken(ast);

		doReturn(TokenTypes.NUM_INT).when(ast).getType(); // operand 3
		test.visitToken(ast);

		doReturn(TokenTypes.DEC).when(ast).getType(); // operator 2
		test.visitToken(ast);

		doReturn(TokenTypes.LOR).when(ast).getType(); // operator 3
		test.visitToken(ast);

		doReturn(TokenTypes.PLUS).when(ast).getType(); // operator 4
		test.visitToken(ast);

		doReturn(TokenTypes.COMMA).when(ast).getType(); // operator 5
		test.visitToken(ast);

		assertEquals(24, test.getCount());
		assertEquals(5, test.getUniqueCount());
	}
}
