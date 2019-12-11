package Deliverable3Tests.whiteBoxTests;

import StructuralMetrics.LinesOfCommentsCheck;
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
public class LinesOfCommentsTest {
	int[] expectedTokens = { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN };
	int[] expectedAcceptableTokens = {TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
	int[] expectedDefaultTokens = {TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	int[] expectedRequiredTokens = new int[0];

	@Test
	public void testGetDefaultTokens() {
		TeamRebecca.LinesOfCommentsCheck test = new TeamRebecca.LinesOfCommentsCheck();

		assertArrayEquals(expectedDefaultTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		TeamRebecca.LinesOfCommentsCheck test = new TeamRebecca.LinesOfCommentsCheck();

		assertArrayEquals(expectedAcceptableTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		TeamRebecca.LinesOfCommentsCheck test = new TeamRebecca.LinesOfCommentsCheck();

		assertArrayEquals(expectedRequiredTokens, test.getRequiredTokens());
	}

	@Test
	public void testCountCommentsCheck1() { //test no comments
		TeamRebecca.LinesOfCommentsCheck test = new TeamRebecca.LinesOfCommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		assertEquals(0, test.getTotalCommentLines());
	}
	
	@Test
	public void testCountCommentsCheck2() { //test single line comment
		TeamRebecca.LinesOfCommentsCheck test = new TeamRebecca.LinesOfCommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(ast).getType();
		test.visitToken(ast);

		assertEquals(1, test.getTotalCommentLines());
	}

	@Test
	public void testCountCommentsCheck3() { //test block comment
		TeamRebecca.LinesOfCommentsCheck test = new TeamRebecca.LinesOfCommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);
		DetailAST token = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(ast).getType();
		doReturn(0).when(ast).getLineNo();
		doReturn(token).when(ast).findFirstToken(TokenTypes.BLOCK_COMMENT_END);
		doReturn(3).when(token).getLineNo();
		doReturn(null).when(ast).getFirstChild();
		doReturn(1).when(ast).getChildCount();
		test.visitToken(ast);

		assertEquals(4, test.getTotalCommentLines());
	}
	
	@Test
	public void testCountCommentsCheck4() { //test a whole bunch of commends
		TeamRebecca.LinesOfCommentsCheck test = new TeamRebecca.LinesOfCommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);
		DetailAST token = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree
		doReturn(1).when(ast).getChildCount();
		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(ast).getType();
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}


		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(ast).getType();
		doReturn(0).when(ast).getLineNo();
		doReturn(token).when(ast).findFirstToken(TokenTypes.BLOCK_COMMENT_END);
		doReturn(0).when(token).getLineNo();
		doReturn(null).when(ast).getFirstChild();

		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		assertEquals(40, test.getTotalCommentLines());
	}
}