package whiteBoxTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.MaintainabilityIndex;
import StructuralMetrics.CyclomaticComplexityCounter;;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DetailAST.class, CyclomaticComplexityCounter.class, MaintainabilityIndex.class})
public class MaintainabilityIndexTest {

	int[] expectedTokens = { TokenTypes.DEC, TokenTypes.INC, TokenTypes.LNOT, TokenTypes.POST_DEC, TokenTypes.POST_INC,
			TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
			TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN,
			TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DIV,
			TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.LAND,
			TokenTypes.LE, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD,
			TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.SL,
			TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.QUESTION,
			TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG };
	
//	@InjectMocks
//	private CyclomaticComplexityCounter cyclomaticComplexity = new CyclomaticComplexityCounter(); // mocking this class
//
//	@Before
//	public void setUp() {
//	    MaintainabilityIndex spy = new 
//	}

	@Test
	public void testGetDefaultTokens() {
		MaintainabilityIndex test = new MaintainabilityIndex();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		MaintainabilityIndex test = new MaintainabilityIndex();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		MaintainabilityIndex test = new MaintainabilityIndex();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	// This is the function that we will be doing all of our tests from, since all
	// the others require mocking private fields thta we have not yet learned how to
	// do.
	// AAA = Arrange, Act, Assert
	@Test
	public void testGetMaintainabilityIndex1() {
		MaintainabilityIndex test = PowerMockito.spy(new MaintainabilityIndex());
		DetailAST ast = PowerMockito.mock(DetailAST.class);
		
		try {
			PowerMockito.doReturn(10).when(test, "getCyclomaticComplexity"); //set CyclomaticComplexity to 10
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

		// MaintainabilityIndex = 171 - 5.2 * log2((operators  +operands) * log2(unique operators + unique operands)) - 0.23 * CyclomaticComplexity - 16.2 * log2(LOC) + 50
		// MaintainabilityIndex = 171 - 5.2 * log2((1  +1) * log2(1 + 1)) - 0.23 * 10 - 16.2 * log2(100) + 50
		assertEquals(2, test.getMaintainabilityIndex(), 0.1);
	}

	@Test
	public void testGetMaintainabilityIndex2() {
		MaintainabilityIndex test = PowerMockito.spy(new MaintainabilityIndex());
		DetailAST ast = PowerMockito.mock(DetailAST.class);
		
		try {
			PowerMockito.doReturn(10).when(test, "getCyclomaticComplexity"); //set CyclomaticComplexity to 10
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

		// MaintainabilityIndex = 171 - 5.2 * log2((operators  +operands) * log2(unique operators + unique operands)) - 0.23 * CyclomaticComplexity - 16.2 * log2(LOC) + 50
		// MaintainabilityIndex = 171 - 5.2 * log2((20  +1) * log2(1 + 1)) - 0.23 * 10 - 16.2 * log2(100) + 50
		assertEquals(88.2, test.getMaintainabilityIndex(), 0.8);
	}

	@Test
	public void testGetMaintainabilityIndex3() {
		MaintainabilityIndex test = PowerMockito.spy(new MaintainabilityIndex());
		DetailAST ast = PowerMockito.mock(DetailAST.class);
		
		try {
			PowerMockito.doReturn(10).when(test, "getCyclomaticComplexity"); //set CyclomaticComplexity to 10
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

		// MaintainabilityIndex = 171 - 5.2 * log2((operators  +operands) * log2(unique operators + unique operands)) - 0.23 * CyclomaticComplexity - 16.2 * log2(LOC) + 50
		// MaintainabilityIndex = 171 - 5.2 * log2((20  +1) * log2(1 + 1)) - 0.23 * 10 - 16.2 * log2(100) + 50
		assertEquals(88.2, test.getMaintainabilityIndex(), 0.8);
	}

	@Test
	public void testGetMaintainabilityIndex4() {
		MaintainabilityIndex test = PowerMockito.spy(new MaintainabilityIndex());
		DetailAST ast = PowerMockito.mock(DetailAST.class);
		
		try {
			PowerMockito.doReturn(10).when(test, "getCyclomaticComplexity"); //set CyclomaticComplexity to 10
			PowerMockito.doReturn(100).when(test, "getLOC"); // mock the number of lines to be 100
		} catch (Exception e) {
			e.printStackTrace();
		}

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

		// MaintainabilityIndex = 171 - 5.2 * log2((operators  +operands) * log2(unique operators + unique operands)) - 0.23 * CyclomaticComplexity - 16.2 * log2(LOC) + 50
		// MaintainabilityIndex = 171 - 5.2 * log2((22  +24) * log2(5 + 3)) - 0.23 * 10 - 16.2 * log2(100) + 50
		assertEquals(74.105, test.getMaintainabilityIndex(), 0.7);
	}
}
