package test;

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
@PrepareForTest({DetailAST.class})
public class ExternalMethodCheckTest {
	
	ExternalMethodCheck extChk = new ExternalMethodCheck();
	DetailAST ast = PowerMockito.mock(DetailAST.class);
	
	@Test
	public void testGetDefaultTokens() {
		assertArrayEquals(new int[] {TokenTypes.METHOD_CALL}, extChk.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		assertArrayEquals(new int[] {TokenTypes.METHOD_CALL}, extChk.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		assertArrayEquals(new int[0], extChk.getRequiredTokens());
	} 

	@Test
	public void testVisitTokenDetailAST() {
		extChk.beginTree(ast);
		doReturn(TokenTypes.METHOD_CALL).when(ast).getType();
		doReturn(TokenTypes.DOT).when(ast).getType();
		//not sure how to return a token type inside of a method. 
		extChk.visitToken(ast);
		//still zero because ast.findfirsttoken inside visit token evaluates to null
		assertEquals(1, extChk.getCount());
	}

	@Test
	public void testBeginTreeDetailAST() {
		assertEquals(0, extChk.getCount());
	}

}
