package StructuralMetrics;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DetailAST.class})
public class VariableCountCheckTest {

VariableCountCheck varChk = new VariableCountCheck();
DetailAST ast = PowerMockito.mock(DetailAST.class);
	
	@Test
	public void testGetDefaultTokens() {
		assertArrayEquals(new int[] {TokenTypes.VARIABLE_DEF}, varChk.getDefaultTokens());
	}

	@Test 
	public void testGetAcceptableTokens() {
		assertArrayEquals(new int[] {TokenTypes.VARIABLE_DEF}, varChk.getAcceptableTokens());
	}
 
	@Test
	public void testGetRequiredTokens() {
		assertArrayEquals(new int[0], varChk.getRequiredTokens());
	}

	@Test
	public void testVisitTokenDetailAST() {
		varChk.visitToken(ast);
		
		assertEquals(1, varChk.getCount());
	}

	@Test
	public void testBeginTreeDetailAST() {
		assertEquals(0, varChk.getCount());
	}
	
	@Test
	public void testGetActualCount() {
		assertEquals(0, varChk.getActualCount());
	}
	
	@Test
	public void testGetVariableCount() {
		assertEquals(0, varChk.getVariableCount());
	}

}
