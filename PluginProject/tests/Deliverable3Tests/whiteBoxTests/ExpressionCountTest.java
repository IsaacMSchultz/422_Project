/*
* Description: This class tests if the expression count check 
* accurately checks the number of expressions.
*/
package Deliverable3Tests.whiteBoxTests;

import StructuralMetrics.ExpressionCountCheck;
import TeamRebecca.ExpressionsCheck;
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
@PrepareForTest({ DetailAST.class })
public class ExpressionCountTest {

	ExpressionsCheck expChk = new ExpressionsCheck();
	DetailAST ast = PowerMockito.mock(DetailAST.class);

	@Test
	public void testGetDefaultTokens() {
		assertArrayEquals(new int[] { TokenTypes.EXPR }, expChk.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		assertArrayEquals(new int[] { TokenTypes.EXPR }, expChk.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		assertArrayEquals(new int[0], expChk.getRequiredTokens());
	}

	@Test
	public void testVisitTokenDetailAST1() {

		expChk.beginTree(ast);
		doReturn(TokenTypes.EXPR).when(ast).getType();

		expChk.visitToken(ast);

		assertEquals(1, expChk.getExpressions());
	}
	
	@Test
	public void testVisitTokenDetailAST2() {

		expChk.beginTree(ast);
		
		doReturn(TokenTypes.EXPR).when(ast).getType();
		for (int i = 0; i < 20; i++) { // do 20 expressions
			expChk.visitToken(ast);
		}

		assertEquals(20, expChk.getExpressions());
	}
	
	@Test
	public void testVisitTokenDetailAST3() {
		expChk.beginTree(ast);

		assertEquals(0, expChk.getExpressions());
	}

}
