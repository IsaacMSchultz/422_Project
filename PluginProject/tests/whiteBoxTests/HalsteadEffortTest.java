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
import StructuralMetrics.HalsteadEffort;
import StructuralMetrics.HalsteadLength;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class HalsteadEffortTest {

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
		HalsteadEffort test = new HalsteadEffort();

		for (int item : test.getDefaultTokens())
			assertTrue(expectedTokens.contains(item));
	}

	@Test
	public void testGetAcceptableTokens() {
		HalsteadEffort test = new HalsteadEffort();

		for (int item : test.getAcceptableTokens())
			assertTrue(expectedTokens.contains(item));
	}

	@Test
	public void testGetRequiredTokens() {
		HalsteadEffort test = new HalsteadEffort();

		for (int item : test.getRequiredTokens())
			assertTrue(expectedTokens.contains(item));
	}
	
	@Test
	public void testVisit() {
		HalsteadEffort test = new HalsteadEffort();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (1 / 2) * (1 / 1) = 1/2
		assertEquals(0.5, test.getHalsteadDifficulty(), 0.1);
		
		// (operators +operands) * log2(unique operators + unique operands)
		// (1+1) * log2(1+1) = 2
		assertEquals(2, test.getHalsteadVolume(), 0.1);
	}

	//////
	// updated correctly mocked whitebox text cases
	//////

	@Test
	public void testGetHalsteadEffort01() {
		HalsteadEffort test = spy(new HalsteadEffort());
		DetailAST ast = new DetailAST();

		doReturn(1.0).when(test).getHalsteadDifficulty();
		doReturn(6.0).when(test).getHalsteadVolume();
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);
		
		// effort = volume * difficulty
		// effort = 1*6 = 6
		assertEquals(6, test.getHalsteadEffort(), 0.1);
	}
	
	@Test
	public void testGetHalsteadEffort02() {
		HalsteadEffort test = spy(new HalsteadEffort());
		DetailAST ast = new DetailAST();

		doReturn(135.0).when(test).getHalsteadDifficulty();
		doReturn(65.0).when(test).getHalsteadVolume();
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// effort = volume * difficulty
		// effort = 135*65 =
		assertEquals(8775 , test.getHalsteadEffort(), 0.1);
	}

	//////
	// old test cases updated (still work!)
	//////

	@Test
	public void testGetHalsteadEffort1() {
		HalsteadEffort test = new HalsteadEffort();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		//((uniqueOperators / 2) * (operands / uniqueOperands)) * ((operators +operands) * log2(unique operators + unique operands))
		//((1 / 2) * (1 / 1)) * ((1 + 1) * log2(1 + 1)) = 1
		assertEquals(1, test.getHalsteadEffort(), 0.1);
	}

	@Test
	public void testGetHalsteadEffort2() {
		HalsteadEffort test = new HalsteadEffort();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		for (int i = 0; i < 20; i++) { // do 20 operands
			test.visitToken(ast);
		}

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		//((uniqueOperators / 2) * (operands / uniqueOperands)) * ((operators +operands) * log2(unique operators + unique operands))
		// uniqueOperators = 1, uniqueOperands = 1, operands = 20, operators = 1
		//((1 / 2) * (20 / 1)) * ((1 + 20) * log2(1 + 1)) = 210
		assertEquals(210, test.getHalsteadEffort(), 0.1);
	}

	@Test
	public void testGetHalsteadEffort3() {
		HalsteadEffort test = new HalsteadEffort();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand

		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		test.finishTree(ast);

		//((uniqueOperators / 2) * (operands / uniqueOperands)) * ((operators +operands) * log2(unique operators + unique operands))
		// uniqueOperators = 1, uniqueOperands = 1, operands = 1, operators = 20
		//((1 / 2) * (1 / 1)) * ((20 + 1) * log2(1 + 1)) = 10.5
		assertEquals(10.5, test.getHalsteadEffort(), 0.1);
	}

	@Test
	public void testGetHalsteadEffort4() { //try a ton of different operators and operands
		HalsteadEffort test = new HalsteadEffort();
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

		//((uniqueOperators / 2) * (operands / uniqueOperands)) * ((operators +operands) * log2(unique operators + unique operands))
		// uniqueOperators = 5, uniqueOperands = 3, operands = 22, operators = 24
		//((5 / 2) * (22 / 3)) * ((22 + 24) * log2(3 + 5)) = 2530
		assertEquals(2530, test.getHalsteadEffort(), 0.1);
	}
}
