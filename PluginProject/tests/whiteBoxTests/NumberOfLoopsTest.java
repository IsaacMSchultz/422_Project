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
	public void testLoop1() { //test no loops
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		test.beginTree(ast); // begin the tree
		test.finishTree(ast);

		assertEquals(0, test.getLoopCount());
	}

	@Test
	public void testLoop2() { //test FOR loop
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		doReturn(TokenTypes.LITERAL_FOR).when(ast).getType();
		test.visitToken(ast);
		test.finishTree(ast);

		assertEquals(1, test.getLoopCount());
	}

	@Test
	public void testLoop3() { //test WHILE loop
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		doReturn(TokenTypes.LITERAL_WHILE).when(ast).getType();
		test.visitToken(ast);
		
		test.finishTree(ast);

		assertEquals(1, test.getLoopCount());
	}
	
	@Test
	public void testLoop4() { //test DO loop
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		doReturn(TokenTypes.LITERAL_DO).when(ast).getType();
		test.visitToken(ast);
		
		test.finishTree(ast);

		assertEquals(1, test.getLoopCount());
	}
	
	@Test
	public void testLoop5() { //test tons of all the kinds of loops
		NumberOfLoopsCheck test = new NumberOfLoopsCheck();
		DetailAST ast = PowerMockito.mock(DetailAST.class);

		doReturn(TokenTypes.LITERAL_DO).when(ast).getType();
		for (int i = 0; i < 20; i++) { // do 20 expressions
			test.visitToken(ast);
		}
		
		doReturn(TokenTypes.LITERAL_FOR).when(ast).getType();
		for (int i = 0; i < 20; i++) { // do 20 expressions
			test.visitToken(ast);
		}
		
		doReturn(TokenTypes.LITERAL_WHILE).when(ast).getType();
		for (int i = 0; i < 20; i++) { // do 20 expressions
			test.visitToken(ast);
		}

		test.finishTree(ast);
		
		assertEquals(60, test.getLoopCount());
	}
}