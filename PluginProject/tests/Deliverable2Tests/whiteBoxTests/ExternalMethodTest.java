/*
* Description: This class tests if the external method check
* accurately checks the number of external methods. 
*/
package Deliverable2Tests.whiteBoxTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.ExternalMethodCheck;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DetailAST.class })
public class ExternalMethodTest {
	

	ExternalMethodCheck extChk = new ExternalMethodCheck();
	DetailAST ast = PowerMockito.mock(DetailAST.class);

	@Test
	public void testGetDefaultTokens() {
		assertArrayEquals(new int[] { TokenTypes.METHOD_CALL }, extChk.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		assertArrayEquals(new int[] { TokenTypes.METHOD_CALL }, extChk.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		assertArrayEquals(new int[0], extChk.getRequiredTokens());
	}

	@Test
	public void testVisitTokenDetailAST1() {
		extChk.beginTree(ast);
		doReturn(TokenTypes.METHOD_CALL).when(ast).getType();
		//not sure how to return a token type inside of a method. 
		extChk.visitToken(ast);
		//still zero because ast.findfirsttoken inside visit token evaluates to null
		assertEquals(0, extChk.getCount());
	}
	
	@Test
	public void testVisitTokenDetailAST2() {
		extChk.beginTree(ast);
		
		DetailAST testAST = PowerMockito.mock(DetailAST.class); //create an AST that we can mock as the parent of the method call token
		
		doReturn(TokenTypes.DOT).when(testAST).getType(); //mock the parent being a DOT
		
		doReturn(TokenTypes.METHOD_CALL).when(ast).getType(); //mock the AST being a method call
		doReturn(testAST).when(ast).findFirstToken(TokenTypes.DOT); //mock the child to return our mocked parent

		extChk.visitToken(ast);

		assertEquals(1, extChk.getCount()); //should be an external method call
	}

	@Test
	public void testBeginTreeDetailAST() {
		assertEquals(0, extChk.getCount());
	}

}
