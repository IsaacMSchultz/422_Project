/* 
* Description: This class tests if the variable count check
* is accurately counting the variables in a file. 
*/
package Deliverable3Tests.whiteBoxTests;

import TeamRebecca.VariablesCheck;
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
public class VariableCountTest {

	VariablesCheck varChk = new VariablesCheck();
	DetailAST ast = PowerMockito.mock(DetailAST.class);

	int[] expectedTokens  = { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };

	@Test
	public void testGetDefaultTokens() {
		assertArrayEquals(expectedTokens, varChk.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		assertArrayEquals(expectedTokens, varChk.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		assertArrayEquals(new int[0], varChk.getRequiredTokens());
	}

	@Test
	public void testVisitTokenDetailAST() {
		varChk.visitToken(ast);
		doReturn(ast).when(ast).findFirstToken(TokenTypes.OBJBLOCK);
		doReturn(1).when(ast).getChildCount();
		doReturn(1).when(ast).getChildCount(TokenTypes.VARIABLE_DEF);
		doReturn(null).when(ast).getFirstChild();

		assertEquals(1, varChk.getVariablesCount());
	}

	@Test
	public void testBeginTreeDetailAST() {
		assertEquals(0, varChk.getVariablesCount());
	}

	@Test
	public void testGetVariableCount() {
		assertEquals(0, varChk.getVariablesCount());
	}

}
