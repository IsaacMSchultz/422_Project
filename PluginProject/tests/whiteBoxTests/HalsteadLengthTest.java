package whiteBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import StructuralMetrics.HalsteadEffort;
import StructuralMetrics.HalsteadLength;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class HalsteadLengthTest {

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
		HalsteadLength test = new HalsteadLength();

		for (int item : test.getDefaultTokens())
			assertTrue(expectedTokens.contains(item));
	}

	@Test
	public void testGetAcceptableTokens() {
		HalsteadLength test = new HalsteadLength();

		for (int item : test.getAcceptableTokens())
			assertTrue(expectedTokens.contains(item));
	}

	@Test
	public void testGetRequiredTokens() {
		HalsteadLength test = new HalsteadLength();

		for (int item : test.getRequiredTokens())
			assertTrue(expectedTokens.contains(item));
	}

	@Test
	public void testVisit() {
		HalsteadLength test = new HalsteadLength();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		assertEquals(1, test.getOperandCount());
		assertEquals(1, test.getOperatorCount());
	}
	
	//////
	// updated correctly mocked whitebox text cases
	//////

	@Test
	public void testGetHalsteadLength01() {
		HalsteadLength test = spy(new HalsteadLength());
		DetailAST ast = new DetailAST();

		doReturn(1).when(test).getOperandCount(); // operand
		doReturn(1).when(test).getOperatorCount(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		assertEquals(2, test.getHalsteadLength());
	}
	
	@Test
	public void testGetHalsteadLength02() {
		HalsteadLength test = spy(new HalsteadLength());
		DetailAST ast = new DetailAST();

		doReturn(135).when(test).getOperandCount(); // operand
		doReturn(65).when(test).getOperatorCount(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		assertEquals(200, test.getHalsteadLength());
	}

	//////
	// old test cases updated (still work!)
	//////

	@Test
	public void testGetHalsteadLength1() { // try with one operand and one operator
		HalsteadLength test = new HalsteadLength();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		assertEquals(2, test.getHalsteadLength());
	}

	@Test
	public void testGetHalsteadLength2() { //try 20 of the same operand and one operator
		HalsteadLength test = new HalsteadLength();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		for (int i = 0; i < 20; i++) { // do 20 operands
			test.visitToken(ast);
		}

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		assertEquals(21, test.getHalsteadLength());
	}

	@Test
	public void testGetHalsteadLength3() { //try one operand and 20 of the same operator
		HalsteadLength test = new HalsteadLength();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand

		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		test.finishTree(ast);

		assertEquals(21, test.getHalsteadLength());
	}

	@Test
	public void testGetHalsteadLength4() { // try a whole bunch of different operators and operands
		HalsteadLength test = new HalsteadLength();
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

		test.finishTree(ast);

		assertEquals(46, test.getHalsteadLength());
	}
}
