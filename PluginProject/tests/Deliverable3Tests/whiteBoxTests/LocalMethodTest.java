/*
* Description: This class tests if the local method check
* accurately counts the local methods
*/
package Deliverable3Tests.whiteBoxTests;

import StructuralMetrics.LocalMethodCheck;
import TeamRebecca.LocalMethodsCheck;
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
public class LocalMethodTest {

	LocalMethodsCheck locChk = new LocalMethodsCheck();
	DetailAST ast = PowerMockito.mock(DetailAST.class);

	@Test
	public void testGetDefaultTokens() {
		assertArrayEquals(new int[] { TokenTypes.METHOD_CALL }, locChk.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		assertArrayEquals(new int[] { TokenTypes.METHOD_CALL }, locChk.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		assertArrayEquals(new int[0], locChk.getRequiredTokens());
	}

	@Test
	public void testVisitTokenDetailAST1() { //try just a method call token
		locChk.beginTree(ast);
		doReturn(TokenTypes.METHOD_CALL).when(ast).getType();

		locChk.visitToken(ast);

		assertEquals(1, locChk.getLocalMethods());
	}
	
	@Test
	public void testVisitTokenDetailAST2() { //test to see when it is a external method call
		locChk.beginTree(ast);
		
		DetailAST testAST = PowerMockito.mock(DetailAST.class); //create an AST that we can mock as the parent of the method call token
		
		doReturn(TokenTypes.DOT).when(testAST).getType(); //mock the parent being a DOT
		
		doReturn(TokenTypes.METHOD_CALL).when(ast).getType(); //mock the AST being a method call
		doReturn(testAST).when(ast).findFirstToken(TokenTypes.DOT); //mock the child to return our mocked parent

		locChk.visitToken(ast);

		assertEquals(0, locChk.getLocalMethods()); //should be an external method call
	}

	@Test
	public void testBeginTreeDetailAST() { //no tokens
		assertEquals(0, locChk.getLocalMethods());
	}

}
