/*
* Description: This class tests if the local method check
* accurately counts the local methods
*/
package whiteBoxTests;

import static org.junit.Assert.*;

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
	public void testVisitTokenDetailAST() {
		locChk.visitToken(ast);
		assertEquals(1, locChk.getCount());
	}

	@Test
	public void testBeginTreeDetailAST() {
		assertEquals(0, locChk.getCount());
	}

}
