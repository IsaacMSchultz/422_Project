package Deliverable3Tests.whiteBoxTests;

import TeamRebecca.HalsteadMetricsCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class TestHalsteadVisitToken {

	@Test
	public void testVisit() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(2).when(test).getLOC(); // need to mock the lines of code cause its run each time.

		DetailAST ast = PowerMockito.mock(DetailAST.class); //parent
		DetailAST objBlock = PowerMockito.mock(DetailAST.class); //child
		DetailAST child = PowerMockito.mock(DetailAST.class); //grandchild
		DetailAST GrandChild = PowerMockito.mock(DetailAST.class); //grandchild
		DetailAST GreatGrandChild = PowerMockito.mock(DetailAST.class); //grandchild

		DetailAST textAST = PowerMockito.mock(DetailAST.class);

		//additional mocking to deal with Dan's Treewalker
		doReturn(objBlock).when(ast).findFirstToken(anyInt()); // mock ObjBlock creation
		doReturn(child).when(objBlock).getFirstChild(); // Mock Child creation
		doReturn(GrandChild).when(child).getFirstChild(); //mock the great great great granchild
		doReturn(GreatGrandChild).when(GrandChild).getFirstChild(); //mock the great great great granchild

		doReturn(textAST).when(child).findFirstToken(TokenTypes.IDENT); //mock getting text from child
		doReturn(GrandChild).when(child).findFirstToken(TokenTypes.SLIST); //mock getting operators from method definition

		doReturn(1).when(child).getChildCount(); //mock 1 child
		doReturn(1).when(GrandChild).getChildCount(); //stop the loop when reaching here
		doReturn(0).when(GreatGrandChild).getChildCount(); //stop the loop when reaching here


		test.beginTree(ast); // begin the tree


		doReturn(TokenTypes.VARIABLE_DEF).when(child).getType(); // operand (with implied operator)
		doReturn(TokenTypes.NUM_DOUBLE).when(GreatGrandChild).getType(); //operand
		doReturn(1).when(GrandChild).getChildCount(TokenTypes.NUM_DOUBLE); //stop the loop when reaching here
		doReturn(false).when(objBlock).branchContains(TokenTypes.NUM_DOUBLE); // not an operator
		doReturn("double").when(textAST).getText(); //mock the name that the treewalker needs
		test.visitToken(ast);

		doReturn(TokenTypes.METHOD_DEF).when(child).getType(); // operator
		doReturn(TokenTypes.ASSIGN).when(GrandChild).getType(); // operator
		doReturn(1).when(GrandChild).getChildCount(TokenTypes.ASSIGN); //stop the loop when reaching here
		doReturn(true).when(objBlock).branchContains(TokenTypes.ASSIGN); // need to mock some other attributes for Dan's treewalker
//		doReturn("assign").when(textAST).getText(); //mock the name that the treewalker needs
		test.visitToken(ast); // This should re-count the grand-child for operands too since it is not re-assigned.

		test.finishTree(ast);

		int ops = test.getOperatorsCount();
		int ands = test.getOperandsCount();
		int uops = test.getUniqueOperators();
		int uands =  test.getUniqueOperands();

		assertEquals(3.0, test.getOperandsCount(), 0.1);
		assertEquals(1.0, test.getOperatorsCount(), 0.1);
		assertEquals(1.0, test.getUniqueOperators(), 0.1);
		assertEquals(2.0 , test.getUniqueOperands(), 0.1); // Only increases the number of unique operands when has a child of type IDENT. Which means it only counts variables as operands.
	}
}
