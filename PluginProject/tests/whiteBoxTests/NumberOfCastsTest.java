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
import StructuralMetrics.NumberOfCastsCheck;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class NumberOfCastsTest {

	int[] expectedTokens = { TokenTypes.TYPECAST };

	@Test
	public void testGetDefaultTokens() {
		NumberOfCastsCheck test = new NumberOfCastsCheck();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		NumberOfCastsCheck test = new NumberOfCastsCheck();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		NumberOfCastsCheck test = new NumberOfCastsCheck();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	@Test
	public void testGetCasts1() { //test 1 cast
		NumberOfCastsCheck test = new NumberOfCastsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.TYPECAST).when(ast).getType(); // operand
		test.visitToken(ast);

		assertEquals(1, test.getCount());
	}

	@Test
	public void testGetCasts2() { //test tons of casts
		NumberOfCastsCheck test = new NumberOfCastsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.TYPECAST).when(ast).getType(); // operand
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		assertEquals(20, test.getCount());
	}
}
