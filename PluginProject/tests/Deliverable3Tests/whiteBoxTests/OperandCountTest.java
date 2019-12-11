package Deliverable3Tests.whiteBoxTests;


import TeamRebecca.HalsteadMetricsCheck;
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
@PrepareForTest(DetailAST.class)
public class OperandCountTest {

    int[] expectedTokens = {TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT,
            TokenTypes.NUM_LONG};

    @Test
    public void testVisit() {
        HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
        doReturn(2).when(test).getLOC(); // need to mock the lines of code cause its run each time.
		DetailAST ast1 = PowerMockito.mock(DetailAST.class); //parent

		test.beginTree(ast1); // begin the tree

		for (int type : expectedTokens) {

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

			doReturn(TokenTypes.VARIABLE_DEF).when(child).getType(); // operand (with implied operator)
			doReturn(type).when(GreatGrandChild).getType(); //operand
			doReturn(1).when(GrandChild).getChildCount(type); //stop the loop when reaching here
			doReturn(false).when(objBlock).branchContains(type); // not an operator
			doReturn("operand" + type).when(textAST).getText(); //mock the name that the treewalker needs
			doReturn("operandName" + type).when(GreatGrandChild).getText(); //mock the name that the treewalker needs
			test.visitToken(ast);
		}

        // each var_def is considered a unique operator implicitly.
        // Total unique: 6 since IDENT count as an additional unique according to their code.
		// numbers such as int and double are not counted as unique operands
        // Total operands are counted as 10

        test.finishTree(ast1);

        int ops = test.getOperatorsCount();
        int ands = test.getOperandsCount();
        int uops = test.getUniqueOperators();
        int uands = test.getUniqueOperands();

        assertEquals(10, test.getOperandsCount());
        assertEquals(6, test.getUniqueOperands()); // Only increases the number of unique operands when has a child of type IDENT. Which means it only counts variables as operands.
    }
}
