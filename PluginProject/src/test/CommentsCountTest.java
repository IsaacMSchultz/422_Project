package test;

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
import StructuralMetrics.CommentsCheck;

public class CommentsCountTest {
	int[] expectedTokens = { TokenTypes.COMMENT_CONTENT };

	@Test
	public void testBeginTree() {
		CommentsCheck test = new CommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast);
		assertEquals(0, test.getCountComments());
	}

	@Test
	public void testGetDefaultTokens() {
		CommentsCheck test = new CommentsCheck();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		CommentsCheck test = new CommentsCheck();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		CommentsCheck test = new CommentsCheck();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	// This is the function that we will be doing all of our tests from, since all
	// the others require mocking private fields thta we have not yet learned how to
	// do.
	// AAA = Arrange, Act, Assert
	@Test
	public void testCountCommentsCheck() {
		CommentsCheck test = new CommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.COMMENT_CONTENT).when(ast).getType();
		test.visitToken(ast);

		doReturn(TokenTypes.COMMENT_CONTENT).when(ast).getType();
		test.visitToken(ast);

		test.finishTree(ast);

		assertEquals(2, test.getCountComments());
	}
}
