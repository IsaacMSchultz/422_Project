package Deliverable3Tests.whiteBoxTests;

import TeamRebecca.HalsteadMetricsCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class HalsteadDifficultyTest {

	Integer[] tokens = { TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR,
			TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN,
			TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT,
			TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND,
			TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT,
			TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL,
			TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION,
			TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR,
			TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, TokenTypes.IDENT,
			TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG };
	
	HashSet<Integer> expectedTokens = new HashSet<Integer>(Arrays.asList(tokens));

	@Test
	public void testGetDefaultTokens() {
		HalsteadMetricsCheck test = new HalsteadMetricsCheck();

		List<Integer> toks = Arrays.stream(test.getDefaultTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test
	public void testGetAcceptableTokens() {
		HalsteadMetricsCheck test = new HalsteadMetricsCheck();
		
		List<Integer> toks = Arrays.stream(test.getAcceptableTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}

	@Test
	public void testGetRequiredTokens() {
		HalsteadMetricsCheck test = new HalsteadMetricsCheck();
		List<Integer> toks = Arrays.stream(test.getRequiredTokens()).boxed().collect(Collectors.toList());
		HashSet<Integer> actualTokens = new HashSet<Integer>(toks);

		for (int token : expectedTokens)
			assertTrue(actualTokens.contains(token));
	}
	
	@Test
	public void testVisit() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(2).when(test).getLOC(); // need to mock the lines of code cause its run each time.

		DetailAST ast = PowerMockito.mock(DetailAST.class); //parent
		DetailAST objBlock = PowerMockito.mock(DetailAST.class); //child
		DetailAST child = PowerMockito.mock(DetailAST.class); //grandchild
		DetailAST GrandChild = PowerMockito.mock(DetailAST.class); //grandchild
		DetailAST GreatGrandChild = PowerMockito.mock(DetailAST.class); //grandchild

		DetailAST textAST = PowerMockito.mock(DetailAST.class);


		//additional mocking to deal with Dan's Treewalker
		doReturn(objBlock).when(ast).findFirstToken(anyInt()); // mock ObjBlock creation
		doReturn(child).when(objBlock).getFirstChild(); // Mock Child creation
		doReturn(textAST).when(child).findFirstToken(anyInt()); //mock getting text from child
		doReturn(GrandChild).when(child).getFirstChild(); //mock the great great great granchild
		doReturn(GreatGrandChild).when(GrandChild).getFirstChild(); //mock the great great great granchild

		doReturn(1).when(child).getChildCount(); //mock 1 child
		doReturn(1).when(GrandChild).getChildCount(); //stop the loop when reaching here
		doReturn(0).when(GreatGrandChild).getChildCount(); //stop the loop when reaching here


		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.VARIABLE_DEF).when(child).getType(); // operand (with implied operator)
		doReturn(TokenTypes.NUM_DOUBLE).when(GreatGrandChild).getType(); //operand
		doReturn(false).when(objBlock).branchContains(TokenTypes.NUM_DOUBLE); // not an operator
		doReturn("double").when(textAST).getText(); //mock the name that the treewalker needs
		test.visitToken(ast);

		doReturn(TokenTypes.ASSIGN).when(ast).getType(); // operator
		doReturn(TokenTypes.ASSIGN).when(GreatGrandChild).getType(); // operator
		doReturn(true).when(objBlock).branchContains(TokenTypes.ASSIGN); // need to mock some other attributes for Dan's treewalker
		doReturn("assign").when(textAST).getText(); //mock the name that the treewalker needs
		test.visitToken(ast);

		test.finishTree(ast);

		assertEquals(1.0, test.getUniqueOperators(), 0.1);
		assertEquals(2.0 , test.getUniqueOperands(), 0.1);
		assertEquals(1.0, test.getOperandsCount(), 0.1);
	}

	@Mock
	HalsteadMetricsCheck tester = mock(HalsteadMetricsCheck.class);

	@Test
	public void testGetHalsteadDifficulty1() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		DetailAST ast = new DetailAST();

		doReturn(1.0).when(test).getOperandsCount(); // operand
		doReturn(1.0).when(test).getUniqueOperators(); // operator
		doReturn(1.0).when(test).getUniqueOperands(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (1 / 2) * (1 / 1) = 1/2
		assertEquals(0.5, test.getHalsteadDifficulty(), 0.1);
	}
	
	@Test
	public void testGetHalsteadDifficulty2() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		DetailAST ast = new DetailAST();

		doReturn(20.0).when(test).getOperandsCount(); // operand
		doReturn(1.0).when(test).getUniqueOperators(); // operator
		doReturn(1.0).when(test).getUniqueOperands(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (1 / 2) * (20 / 1) = 10
		assertEquals(10, test.getHalsteadDifficulty(), 0.1);
	}
	
	@Test
	public void testGetHalsteadDifficulty3() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		DetailAST ast = new DetailAST();

		doReturn(20.0).when(test).getOperandsCount(); // operand
		doReturn(20.0).when(test).getUniqueOperators(); // operator
		doReturn(1.0).when(test).getUniqueOperands(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (20 / 2) * (20 / 1) = 200
		assertEquals(200, test.getHalsteadDifficulty(), 0.1);
	}
	
	@Test
	public void testGetHalsteadDifficulty4() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		DetailAST ast = new DetailAST();

		doReturn(37.0).when(test).getOperandsCount(); // operand
		doReturn(15.0).when(test).getUniqueOperators(); // operator
		doReturn(11.0).when(test).getUniqueOperands(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (15 / 2) * (37 / 11) = 25.22727
		assertEquals(25.22727, test.getHalsteadDifficulty(), 0.1);
	}
}
