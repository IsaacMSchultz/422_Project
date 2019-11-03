package whiteBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import StructuralMetrics.HalsteadDifficulty;
import StructuralMetrics.HalsteadVocabulary;
import StructuralMetrics.HalsteadVolume;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class HalsteadVolumeTest {

	Integer[] tokens = { TokenTypes.DEC, TokenTypes.INC, TokenTypes.LNOT, TokenTypes.POST_DEC, TokenTypes.POST_INC,
			TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
			TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN,
			TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DIV,
			TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.LAND,
			TokenTypes.LE, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD,
			TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.SL,
			TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.QUESTION,
			TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG };

	HashSet<Integer> expectedTokens = new HashSet<Integer>(Arrays.asList(tokens));

	@Test
	public void testGetDefaultTokens() {
		HalsteadVolume test = new HalsteadVolume();

		for (int item : test.getDefaultTokens())
			assertTrue(expectedTokens.contains(item));
	}

	@Test
	public void testGetAcceptableTokens() {
		HalsteadVolume test = new HalsteadVolume();

		for (int item : test.getAcceptableTokens())
			assertTrue(expectedTokens.contains(item));
	}

	@Test
	public void testGetRequiredTokens() {
		HalsteadVolume test = new HalsteadVolume();

		for (int item : test.getRequiredTokens())
			assertTrue(expectedTokens.contains(item));
	}

	//////
	// updated correctly mocked whitebox text cases
	//////
	
	@Test
	public void testGetHalsteadVolume01() { //test with known values
		HalsteadVolume test = spy(new HalsteadVolume());
		DetailAST ast = new DetailAST();

		doReturn(37).when(test).getHalsteadLength();
		doReturn(16).when(test).getHalsteadVocabulary();
		test.beginTree(ast); // begin the tree
		test.finishTree(ast);

		// length * log2(vocabulary)
		// 37 * log2(16) = 148
		assertEquals(148, test.getHalsteadVolume(), 0.1);
	}

	@Test
	public void testGetHalsteadVolume02() { //test with known values
		HalsteadVolume test = spy(new HalsteadVolume());
		DetailAST ast = new DetailAST();

		doReturn(99).when(test).getHalsteadLength();
		doReturn(39).when(test).getHalsteadVocabulary();
		test.beginTree(ast); // begin the tree
		test.finishTree(ast);

		// length * log2(vocabulary)
		// 99 * log2(39) = 
		assertEquals(523.25481, test.getHalsteadVolume(), 0.1);
	}
	
	//////
	// old test cases updated (still work!)
	//////

	@Test
	public void testGetHalsteadVolume1() { //test with a single operator and operand
		HalsteadVolume test = new HalsteadVolume();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		// (operators +operands) * log2(unique operators + unique operands)
		assertEquals(2, test.getHalsteadVolume(), 0.1);
	}

	@Test
	public void testGetHalsteadVolume2() { //test with tons of one operand and one operator
		HalsteadVolume test = new HalsteadVolume();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		for (int i = 0; i < 20; i++) { // do 20 operands
			test.visitToken(ast);
		}

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		// (operators +operands) * log2(unique operators + unique operands)
		assertEquals(21, test.getHalsteadVolume(), 0.1);
	}

	@Test
	public void testGetHalsteadVolume3() { //test with tons of one operator and one operand
		HalsteadVolume test = new HalsteadVolume();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		doReturn("operand").when(ast).getText();

		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		doReturn("operator").when(ast).getText();
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		test.finishTree(ast);

		// (operators +operands) * log2(unique operators + unique operands)
		assertEquals(21, test.getHalsteadVolume(), 0.1);
	}

	@Test
	public void testGetHalsteadVolume4() { //test with tons of operands and operators 
		HalsteadVolume test = new HalsteadVolume();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand 1
		doReturn("operand1").when(ast).getText();
		for (int i = 0; i < 20; i++) { // do 20 operands
			test.visitToken(ast);
		}

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator 1
		doReturn("operator1").when(ast).getText();
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		// Now lets get some more unique operators and operands in there.

		doReturn(TokenTypes.IDENT).when(ast).getType(); // operand 2
		doReturn("operand2").when(ast).getText();
		test.visitToken(ast);

		doReturn(TokenTypes.NUM_INT).when(ast).getType(); // operand 3
		doReturn("operand3").when(ast).getText();
		test.visitToken(ast);

		doReturn(TokenTypes.DEC).when(ast).getType(); // operator 2
		doReturn("operator2").when(ast).getText();
		test.visitToken(ast);

		doReturn(TokenTypes.LOR).when(ast).getType(); // operator 3
		doReturn("operator3").when(ast).getText();
		test.visitToken(ast);

		doReturn(TokenTypes.PLUS).when(ast).getType(); // operator 4
		doReturn("operator4").when(ast).getText();
		test.visitToken(ast);

		doReturn(TokenTypes.COMMA).when(ast).getType(); // operator 5
		doReturn("operator5").when(ast).getText();
		test.visitToken(ast);

		test.finishTree(ast);

		// (operators +operands) * log2(unique operators + unique operands)
		assertEquals(138, test.getHalsteadVolume(), 0.2);
	}
}
