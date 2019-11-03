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
	
	int[] expectedTokens = {TokenTypes.TYPECAST};

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

	// This is the function that we will be doing all of our tests from, since all
	// the others require mocking private fields thta we have not yet learned how to
	// do.
	// AAA = Arrange, Act, Assert
	@Test
	public void testGetCasts1() {
		NumberOfCastsCheck test = new NumberOfCastsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.TYPECAST).when(ast).getType(); // operand
		test.visitToken(ast);

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		test.visitToken(ast);

		// (operators  +operands) * log2(unique operators + unique operands)
		assertEquals(1, test.getCount());
	}
	
	@Test
	public void testGetCasts2() {
		NumberOfCastsCheck test = new NumberOfCastsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.TYPECAST).when(ast).getType(); // operand
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		doReturn(TokenTypes.LNOT).when(ast).getType(); // operator
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}
		
		// (operators  +operands) * log2(unique operators + unique operands)
		assertEquals(20, test.getCount());
	}
}
