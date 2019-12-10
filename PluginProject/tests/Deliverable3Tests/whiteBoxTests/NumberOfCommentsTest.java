package Deliverable3Tests.whiteBoxTests;

import StructuralMetrics.NumberOfCommentsCheck;
import TeamRebecca.TotalCommentsCheck;
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
public class NumberOfCommentsTest {
	int[] expectedTokens = { TokenTypes.COMMENT_CONTENT };

	@Test
	public void testBeginTree() {
		TotalCommentsCheck test = new TotalCommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast);
		assertEquals(0, test.getCommentLines());
	}

	@Test
	public void testGetDefaultTokens() {
		TotalCommentsCheck test = new TotalCommentsCheck();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		NumberOfCommentsCheck test = new NumberOfCommentsCheck();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		NumberOfCommentsCheck test = new NumberOfCommentsCheck();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	// This is the function that we will be doing all of our tests from, since all
	// the others require mocking private fields thta we have not yet learned how to
	// do.
	// AAA = Arrange, Act, Assert
	@Test
	public void testCountCommentsCount() {
		TotalCommentsCheck test = new TotalCommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.COMMENT_CONTENT).when(ast).getType();
		test.visitToken(ast);

		doReturn(TokenTypes.COMMENT_CONTENT).when(ast).getType();
		test.visitToken(ast);

		assertEquals(2, test.getCommentLines());
	}
}
