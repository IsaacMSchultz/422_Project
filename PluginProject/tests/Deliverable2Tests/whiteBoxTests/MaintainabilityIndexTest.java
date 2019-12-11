// NEEDS TO BE UPDATED

package Deliverable2Tests.whiteBoxTests;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.MaintainabilityIndex;
import StructuralMetrics.CyclomaticComplexityCounter;
;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DetailAST.class, CyclomaticComplexityCounter.class, MaintainabilityIndex.class })
public class MaintainabilityIndexTest {

	Integer[] tokens = { TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR,
			TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN,
			TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT,
			TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND,
			TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT,
			TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL,
			TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION,
			TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR,
			TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, TokenTypes.IDENT,
			TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,
			TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO, TokenTypes.CTOR_DEF,
			TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE,
			TokenTypes.LITERAL_DO, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_SWITCH,
			TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH, TokenTypes.QUESTION, TokenTypes.LAND, TokenTypes.LOR };

	Integer[] tokensAccept = { TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT,
			TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR,
			TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV,
			TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC,
			TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT,
			TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD,
			TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC,
			TokenTypes.POST_INC, TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR,
			TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
			TokenTypes.UNARY_PLUS, TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT,
			TokenTypes.NUM_LONG, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO,
			TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, };

	HashSet<Integer> expectedTokens = new HashSet<Integer>(Arrays.asList(tokens));

	@Test
	public void testGetDefaultTokens() {
		MaintainabilityIndex test = new MaintainabilityIndex();

		List<Integer> toks = Arrays.stream(test.getDefaultTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test
	public void testGetAcceptableTokens() {
		MaintainabilityIndex test = new MaintainabilityIndex();

		List<Integer> toks = Arrays.stream(test.getAcceptableTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test
	public void testGetRequiredTokens() {
		MaintainabilityIndex test = new MaintainabilityIndex();
		List<Integer> toks = Arrays.stream(test.getRequiredTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : tokensAccept)
			assertTrue(actualTokens.contains(token)); // not accounting for all tokens in acceptable tokens within the test
	}

	@Test
	public void testVisit() {
		MaintainabilityIndex test = spy(new MaintainabilityIndex());
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(50234).when(test).getLOC(); // mock LOC

		doReturn(TokenTypes.METHOD_DEF).when(ast).getType(); // loop
		test.visitToken(ast);
		test.leaveToken(ast);
		
		doReturn(TokenTypes.LITERAL_DO).when(ast).getType(); // loop
		test.visitToken(ast);
		test.leaveToken(ast);

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		assertEquals(0, test.getCyclomaticComplexity());

		// (operators +operands) * log2(unique operators + unique operands)
		// (1+1) * log2(1+1) = 2
		assertEquals(2, test.getHalsteadVolume(), 0.1);
	}

	//////
	// updated correctly mocked whitebox text cases
	//////

	@Test
	public void testGetMaintainabilityIndex01() {
		MaintainabilityIndex test = spy(new MaintainabilityIndex());
		DetailAST ast = new DetailAST();

		doReturn(1235.3213).when(test).getHalsteadVolume();
		doReturn(45).when(test).getCyclomaticComplexity();
		doReturn(50234).when(test).getLOC();
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// MaintainabilityIndex = 171 - 5.2 * log2(Volume) - 0.23 * cyclo - 16.2 *
		// log2(LOC) + 50;
		// 171 - 5.2 * log2(1235.3213) - 0.23 * 45 - 16.2 * log2(50234) + 50
		assertEquals(-95.74278, test.getMaintainabilityIndex(), 0.1);
	}

	@Test
	public void testGetMaintainabilityIndex02() {
		MaintainabilityIndex test = spy(new MaintainabilityIndex());
		DetailAST ast = new DetailAST();

		doReturn(9541.564).when(test).getHalsteadVolume();
		doReturn(135).when(test).getCyclomaticComplexity();
		doReturn(165).when(test).getLOC();
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// MaintainabilityIndex = 171 - 5.2 * log2(Volume) - 0.23 * cyclo - 16.2 *
		// log2(LOC) + 50;
		// 171 - 5.2 * log2(9541.564) - 0.23 * 135 - 16.2 * log2(165) + 50
		assertEquals(1.87152, test.getMaintainabilityIndex(), 0.1);
	}

	//////
	// old test cases updated (still work!)
	//////

	@Test
	public void testGetMaintainabilityIndex1() { // try 1 operand and 1 operator with 10 cyclocomplexity and 100 LOC
		MaintainabilityIndex test = PowerMockito.spy(new MaintainabilityIndex());
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		try {
			PowerMockito.doReturn(10).when(test, "getCyclomaticComplexity"); // set CyclomaticComplexity to 10
			PowerMockito.doReturn(100).when(test, "getLOC"); // mock the number of lines to be 100
		} catch (Exception e) {
			e.printStackTrace();
		}

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		// MaintainabilityIndex = 171 - 5.2 * log2(Volume) - 0.23 * cyclo - 16.2 *
		// log2(LOC) + 50;
		// 171 - 5.2 * log2(2) - 0.23 * 10 - 16.2 * log2(100) + 50
		assertEquals(105.86952, test.getMaintainabilityIndex(), 0.1);
	}

	@Test
	public void testGetMaintainabilityIndex2() { // try 20 operands and 1 operator with 10 cyclocomplexity and 100 LOC
		MaintainabilityIndex test = PowerMockito.spy(new MaintainabilityIndex());
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		try {
			PowerMockito.doReturn(10).when(test, "getCyclomaticComplexity"); // set CyclomaticComplexity to 10
			PowerMockito.doReturn(100).when(test, "getLOC"); // mock the number of lines to be 100
		} catch (Exception e) {
			e.printStackTrace();
		}

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand
		for (int i = 0; i < 20; i++) { // do 20 operands
			test.visitToken(ast);
		}

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		test.finishTree(ast);

		// MaintainabilityIndex = 171 - 5.2 * log2(Volume) - 0.23 * cyclo - 16.2 *
		// log2(LOC) + 50;
		// 171 - 5.2 * log2(21) - 0.23 * 10 - 16.2 * log2(100) + 50
		assertEquals(88.22947, test.getMaintainabilityIndex(), 0.1);
	}

	@Test
	public void testGetMaintainabilityIndex3() { // try 1 operand and 20 operators with 10 cyclocomplexity and 100 LOC
		MaintainabilityIndex test = PowerMockito.spy(new MaintainabilityIndex());
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		try {
			PowerMockito.doReturn(10).when(test, "getCyclomaticComplexity"); // set CyclomaticComplexity to 10
			PowerMockito.doReturn(100).when(test, "getLOC"); // mock the number of lines to be 100
		} catch (Exception e) {
			e.printStackTrace();
		}

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.NUM_DOUBLE).when(ast).getType(); // operand

		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		test.finishTree(ast);

		// MaintainabilityIndex = 171 - 5.2 * log2(Volume) - 0.23 * cyclo - 16.2 *
		// log2(LOC) + 50;
		// 171 - 5.2 * log2(21) - 0.23 * 10 - 16.2 * log2(100) + 50
		assertEquals(88.22947, test.getMaintainabilityIndex(), 0.1);
	}

	@Test
	public void testGetMaintainabilityIndex4() { // try a whole bunch of operators and operands with 10 cyclocomplexity
													// and 100 LOC
		MaintainabilityIndex test = PowerMockito.spy(new MaintainabilityIndex());
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		try {
			PowerMockito.doReturn(10).when(test, "getCyclomaticComplexity"); // set CyclomaticComplexity to 10
			PowerMockito.doReturn(100).when(test, "getLOC"); // mock the number of lines to be 100
		} catch (Exception e) {
			e.printStackTrace();
		}

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

		// MaintainabilityIndex = 171 - 5.2 * log2(Volume) - 0.23 * cyclo - 16.2 *
		// log2(LOC) + 50;
		// 171 - 5.2 * log2(138) - 0.23 * 10 - 16.2 * log2(100) + 50
		assertEquals(74.10520, test.getMaintainabilityIndex(), 0.1);
	}
}
