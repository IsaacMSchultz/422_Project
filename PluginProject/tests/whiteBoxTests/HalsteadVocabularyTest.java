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

import StructuralMetrics.HalsteadLength;
import StructuralMetrics.HalsteadVocabulary;
import StructuralMetrics.HalsteadVolume;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class HalsteadVocabularyTest {

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
		HalsteadVocabulary test = new HalsteadVocabulary();

		for (int item : test.getDefaultTokens())
			assertTrue(expectedTokens.contains(item));
	}

	@Test
	public void testGetAcceptableTokens() {
		HalsteadVocabulary test = new HalsteadVocabulary();

		for (int item : test.getAcceptableTokens())
			assertTrue(expectedTokens.contains(item));
	}

	@Test
	public void testGetRequiredTokens() {
		HalsteadVocabulary test = new HalsteadVocabulary();

		for (int item : test.getRequiredTokens())
			assertTrue(expectedTokens.contains(item));
	}
	
	//////
	// updated correctly mocked whitebox text cases
	//////
	
	@Test
	public void testGetHalsteadVocabulary01() { //test with known values
		HalsteadVocabulary test = spy(new HalsteadVocabulary());
		DetailAST ast = new DetailAST();

		doReturn(99).when(test).getUniqueOperandCount();
		doReturn(39).when(test).getUniqueOperatorCount();
		test.beginTree(ast); // begin the tree
		test.finishTree(ast);

		// halsteadVocabulary = uniqueOperands + uniqueOperators;
		// 99 + 39 = 138
		assertEquals(138, test.getHalsteadVocabulary());
	}

	@Test
	public void testGetHalsteadVocabulary02() { //test with known values
		HalsteadVocabulary test = spy(new HalsteadVocabulary());
		DetailAST ast = new DetailAST();

		doReturn(9649).when(test).getUniqueOperandCount();
		doReturn(32).when(test).getUniqueOperatorCount();
		test.beginTree(ast); // begin the tree
		test.finishTree(ast);

		// halsteadVocabulary = uniqueOperands + uniqueOperators;
		// 9649 + 32 = 138
		assertEquals(9681, test.getHalsteadVocabulary());
	}

	//////
	// old test cases updated (still work!)
	//////

	@Test
	public void testGetHalsteadVocabulary1() {
		HalsteadVocabulary test = new HalsteadVocabulary();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		// unique operators + unique operands
		assertEquals(2, test.getHalsteadVocabulary());
	}

	@Test
	public void testGetHalsteadVocabulary2() {
		HalsteadVocabulary test = new HalsteadVocabulary();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		for (int i = 0; i < 20; i++) { // do 20 operands
			test.visitToken(ast);
		}

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		// unique operators + unique operands
		assertEquals(2, test.getHalsteadVocabulary());
	}

	@Test
	public void testGetHalsteadVocabulary3() {
		HalsteadVocabulary test = new HalsteadVocabulary();
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

		// unique operators + unique operands
		assertEquals(2, test.getHalsteadVocabulary());
	}

	@Test
	public void testGetHalsteadVocabulary4() {
		HalsteadVocabulary test = new HalsteadVocabulary();
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

		// unique operators + unique operands
		assertEquals(8, test.getHalsteadVocabulary());
	}
}
