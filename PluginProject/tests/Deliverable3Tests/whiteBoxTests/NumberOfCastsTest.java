package Deliverable3Tests.whiteBoxTests;

import StructuralMetrics.NumberOfCastsCheck;
import TeamRebecca.CastsCheck;
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
@PrepareForTest(DetailAST.class)
public class NumberOfCastsTest {

	int[] expectedTokens = { TokenTypes.TYPECAST };
	int[] acceptableTokens = { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	int[] requiredTokens = new int[0];
	int[] defaultTokens = { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };

	@Test
	public void testGetDefaultTokens() {
		CastsCheck test = new CastsCheck();

		assertArrayEquals(defaultTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		CastsCheck test = new CastsCheck();

		assertArrayEquals(acceptableTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		CastsCheck test = new CastsCheck();

		assertArrayEquals(requiredTokens, test.getRequiredTokens());
	}

	@Test
	public void testGetCasts1() { //test 1 cast
		CastsCheck test = new CastsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.TYPECAST).when(ast).getType(); // operand
		test.visitToken(ast);

		assertEquals(1, test.getCasts());
	}

	@Test
	public void testGetCasts2() { //test tons of casts
		CastsCheck test = new CastsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.TYPECAST).when(ast).getType(); // operand
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		assertEquals(20, test.getCasts());
	}
}
