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
import StructuralMetrics.LinesOfCommentsCheck;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class LinesOfCommentsTest {
	int[] expectedTokens = { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN };

	@Test
	public void testGetDefaultTokens() {
		LinesOfCommentsCheck test = new LinesOfCommentsCheck();

		assertArrayEquals(expectedTokens, test.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		LinesOfCommentsCheck test = new LinesOfCommentsCheck();

		assertArrayEquals(expectedTokens, test.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		LinesOfCommentsCheck test = new LinesOfCommentsCheck();

		assertArrayEquals(expectedTokens, test.getRequiredTokens());
	}

	@Test
	public void testCountCommentsCheck1() { //test no comments
		LinesOfCommentsCheck test = new LinesOfCommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		assertEquals(0, test.getCount());
	}
	
	@Test
	public void testCountCommentsCheck2() { //test single line comment
		LinesOfCommentsCheck test = new LinesOfCommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(ast).getType();
		test.visitToken(ast);

		assertEquals(1, test.getCount());
	}

	@Test
	public void testCountCommentsCheck3() { //test block comment
		LinesOfCommentsCheck test = new LinesOfCommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree

		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(ast).getType();
		test.visitToken(ast);

		assertEquals(1, test.getCount());
	}
	
	@Test
	public void testCountCommentsCheck4() { //test a whole bunch of commends
		LinesOfCommentsCheck test = new LinesOfCommentsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree
		
		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(ast).getType();
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(ast).getType();
		for (int i = 0; i < 20; i++) { // do 20 operators
			test.visitToken(ast);
		}

		assertEquals(40, test.getCount());
	}
}