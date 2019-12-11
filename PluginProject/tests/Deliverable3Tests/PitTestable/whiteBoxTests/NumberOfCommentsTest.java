package Deliverable3Tests.PitTestable.whiteBoxTests;


import TeamRebecca.TotalCommentsCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class NumberOfCommentsTest {
	int[] expectedTokens = { TokenTypes.COMMENT_CONTENT };
	int[] requiredTokens = new int[0];


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
		TotalCommentsCheck test = new TotalCommentsCheck();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		TotalCommentsCheck test = new TotalCommentsCheck();

		assertArrayEquals(requiredTokens, test.getRequiredTokens());
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
		doReturn(ast).when(ast).findFirstToken(TokenTypes.COMMENT_CONTENT);
		test.visitToken(ast);

		doReturn(TokenTypes.COMMENT_CONTENT).when(ast).getType();
		test.visitToken(ast);

		assertEquals(2, test.getCommentLines());
	}

	@Test
	public void testCountCommentsCount2() {
		TotalCommentsCheck test = new TotalCommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.COMMENT_CONTENT).when(ast).getType();
		doReturn(ast).when(ast).findFirstToken(TokenTypes.COMMENT_CONTENT);
		test.visitToken(ast);

		doReturn(TokenTypes.COMMENT_CONTENT).when(ast).getType();
		test.visitToken(ast);

		doReturn(TokenTypes.COMMENT_CONTENT).when(ast).getType();
		doReturn(ast).when(ast).findFirstToken(TokenTypes.COMMENT_CONTENT);
		test.visitToken(ast);

		doReturn(TokenTypes.COMMENT_CONTENT).when(ast).getType();
		test.visitToken(ast);

	     assertEquals(4, test.getCommentLines());
	}
}
