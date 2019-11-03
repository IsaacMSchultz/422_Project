/*
* Description: This class tests if the expression count check 
* accurately checks the number of expressions.
*/
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
import StructuralMetrics.ExpressionCountCheck;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DetailAST.class })
public class ExpressionCountTest {

	ExpressionCountCheck expChk = new ExpressionCountCheck();
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
	public void testVisitTokenDetailAST() {

		expChk.beginTree(ast);
		doReturn(TokenTypes.EXPR).when(ast).getType();

		expChk.visitToken(ast);

		assertEquals(1, expChk.getCount());
	}

}
