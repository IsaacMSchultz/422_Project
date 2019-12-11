/* 
* Description: This class tests if the variable count check
* is accurately counting the variables in a file. 
*/
package Deliverable3Tests.PitTestable.whiteBoxTests;

import TeamRebecca.HalsteadMetricsCheck;
import TeamRebecca.VariablesCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DetailAST.class })
public class VariableCountTest {

	VariablesCheck varChk = new VariablesCheck();
	DetailAST ast = PowerMockito.mock(DetailAST.class);

	int[] expectedTokens  = { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };

	@Test
	public void testGetDefaultTokens() {
		assertArrayEquals(expectedTokens, varChk.getDefaultTokens());
	}

	@Test
	public void testGetAcceptableTokens() {
		assertArrayEquals(expectedTokens, varChk.getAcceptableTokens());
	}

	@Test
	public void testGetRequiredTokens() {
		assertArrayEquals(new int[0], varChk.getRequiredTokens());
	}

//	@Test
//	public void testVisitTokenDetailAST() {
//		varChk.visitToken(ast);
//		doReturn(ast).when(ast).findFirstToken(TokenTypes.OBJBLOCK);
//		doReturn(1).when(ast).getChildCount();
//		doReturn(1).when(ast).getChildCount(TokenTypes.VARIABLE_DEF);
//		doReturn(null).when(ast).getFirstChild();
//
//		assertEquals(1, varChk.getCount());
//
//		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
//		doReturn(2).when(test).getLOC(); // need to mock the lines of code cause its run each time.
//
//		DetailAST ast = PowerMockito.mock(DetailAST.class); //parent
//		DetailAST objBlock = PowerMockito.mock(DetailAST.class); //child
//		DetailAST child = PowerMockito.mock(DetailAST.class); //grandchild
//		DetailAST GrandChild = PowerMockito.mock(DetailAST.class); //grandchild
//		DetailAST GreatGrandChild = PowerMockito.mock(DetailAST.class); //grandchild
//
//		DetailAST textAST = PowerMockito.mock(DetailAST.class);
//
//		//additional mocking to deal with Dan's Treewalker
//		doReturn(objBlock).when(ast).findFirstToken(anyInt()); // mock ObjBlock creation
//		doReturn(child).when(objBlock).getFirstChild(); // Mock Child creation
//		doReturn(GrandChild).when(child).getFirstChild(); //mock the great great great granchild
//		doReturn(GreatGrandChild).when(GrandChild).getFirstChild(); //mock the great great great granchild
//
//		doReturn(textAST).when(child).findFirstToken(TokenTypes.IDENT); //mock getting text from child
//		doReturn(GrandChild).when(child).findFirstToken(TokenTypes.SLIST); //mock getting operators from method definition
//
//		doReturn(1).when(child).getChildCount(); //mock 1 child
//		doReturn(1).when(GrandChild).getChildCount(); //stop the loop when reaching here
//		doReturn(0).when(GreatGrandChild).getChildCount(); //stop the loop when reaching here
//
//
//		test.beginTree(ast); // begin the tree
//
//
//		doReturn(TokenTypes.VARIABLE_DEF).when(child).getType(); // operand (with implied operator)
//		doReturn(TokenTypes.NUM_DOUBLE).when(GreatGrandChild).getType(); //operand
//		doReturn(1).when(GrandChild).getChildCount(TokenTypes.NUM_DOUBLE); //stop the loop when reaching here
//		doReturn(false).when(objBlock).branchContains(TokenTypes.NUM_DOUBLE); // not an operator
//		doReturn("double").when(textAST).getText(); //mock the name that the treewalker needs
//		test.visitToken(ast);
//	}

	@Test
	public void testBeginTreeDetailAST() {
		assertEquals(0, varChk.getCount());
	}

	@Test
	public void testGetVariableCount() {
		assertEquals(0, varChk.getCount());
	}

}
