/*
* Description: This class tests if the local method check
* accurately counts the local methods
*/
package whiteBoxTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.LocalMethodCheck;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DetailAST.class })
public class LocalMethodTest {

	LocalMethodCheck locChk = new LocalMethodCheck();
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
	public void testVisitTokenDetailAST1() {
		locChk.beginTree(ast);
		doReturn(TokenTypes.METHOD_CALL).when(ast).getType();
		//not sure how to return a token type inside of a method. 
		locChk.visitToken(ast);
		//still zero because ast.findfirsttoken inside visit token evaluates to null
		assertEquals(1, locChk.getCount());
	}
	
	@Test
	public void testVisitTokenDetailAST2() {
		locChk.beginTree(ast);
		
		DetailAST testAST = PowerMockito.mock(DetailAST.class); //create an AST that we can mock as the parent of the method call token
		
		doReturn(TokenTypes.DOT).when(testAST).getType(); //mock the parent being a DOT
		
		doReturn(TokenTypes.METHOD_CALL).when(ast).getType(); //mock the AST being a method call
		doReturn(testAST).when(ast).findFirstToken(TokenTypes.DOT); //mock the child to return our mocked parent

		locChk.visitToken(ast);

		assertEquals(0, locChk.getCount()); //should be an external method call
	}

	@Test
	public void testBeginTreeDetailAST() {
		assertEquals(0, locChk.getCount());
	}

}
