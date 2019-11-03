package whiteBoxTests;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.NumberOfLoopsCheck;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class NumberOfLoopsTest {
	int[] expectedTokens = { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO };

	@Test
	public void testGetDefaultTokens() {
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	@Test
	public void testCountCommentsCheck() {
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		assertEquals(0, test.getLoopCount());

		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(ast).getType(); // operand
		test.visitToken(ast);

		assertEquals(1, test.getLoopCount());

		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(ast).getType(); // operator
		test.visitToken(ast);

		assertEquals(2, test.getLoopCount());

		test.finishTree(ast);

	}
}