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
import StructuralMetrics.HalsteadVocabulary;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class HalsteadVocabularyTest {

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
		HalsteadVocabulary test = new HalsteadVocabulary();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		HalsteadVocabulary test = new HalsteadVocabulary();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		HalsteadVocabulary test = new HalsteadVocabulary();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	// This is the function that we will be doing all of our tests from, since all
	// the others require mocking private fields thta we have not yet learned how to
	// do.
	// AAA = Arrange, Act, Assert
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

		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
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

		// unique operators + unique operands
		assertEquals(8, test.getHalsteadVocabulary());
	}
}
