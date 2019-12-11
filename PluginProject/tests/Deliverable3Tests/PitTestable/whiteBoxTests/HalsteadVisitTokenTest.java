package Deliverable3Tests.PitTestable.whiteBoxTests;



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
public class HalsteadVisitTokenTest {

    int[] OurOperators = {TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT,
            TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR,
            TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV,
            TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC,
            TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT,
            TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD,
            TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC,
            TokenTypes.POST_INC, TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR,
            TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
            TokenTypes.UNARY_PLUS};

    int[] TheirOperators = {TokenTypes.ASSIGN, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR,
            TokenTypes.MOD, TokenTypes.LCURLY, TokenTypes.RCURLY, TokenTypes.SLIST, TokenTypes.RPAREN,
            TokenTypes.LPAREN, TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE, TokenTypes.LITERAL_FOR,
            TokenTypes.METHOD_CALL, TokenTypes.DOT, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE,
            TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_RETURN, TokenTypes.LITERAL_BREAK,
            TokenTypes.COMMA, TokenTypes.RBRACK, TokenTypes.ARRAY_DECLARATOR, TokenTypes.ARRAY_INIT,
            TokenTypes.SEMI, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BAND_ASSIGN, TokenTypes.BAND,
            TokenTypes.BSR_ASSIGN, TokenTypes.BSR, TokenTypes.BXOR_ASSIGN, TokenTypes.BXOR, TokenTypes.DIV_ASSIGN,
            TokenTypes.MINUS_ASSIGN, TokenTypes.MOD_ASSIGN, TokenTypes.PLUS_ASSIGN, TokenTypes.SL,
            TokenTypes.SL_ASSIGN, TokenTypes.BNOT, TokenTypes.SR_ASSIGN, TokenTypes.SR, TokenTypes.STAR_ASSIGN,
            TokenTypes.INC, TokenTypes.POST_INC, TokenTypes.DEC, TokenTypes.POST_DEC, TokenTypes.LITERAL_DEFAULT};

    int[] operandTokens = {TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT,
            TokenTypes.NUM_LONG};

//    @Test
//    public void testVisitOurOperators() {
//        HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
//        doReturn(2).when(test).getLOC(); // need to mock the lines of code cause its run each time.
//        DetailAST ast1 = PowerMockito.mock(DetailAST.class); //parent
//
//        test.beginTree(ast1); // begin the tree
//
//        for (int type : OurOperators) {
//
//            DetailAST ast = PowerMockito.mock(DetailAST.class); //parent
//            DetailAST objBlock = PowerMockito.mock(DetailAST.class); //child
//            DetailAST child = PowerMockito.mock(DetailAST.class); //grandchild
//            DetailAST GrandChild = PowerMockito.mock(DetailAST.class); //grandchild
//            DetailAST GreatGrandChild = PowerMockito.mock(DetailAST.class); //grandchild
//
//            DetailAST textAST = PowerMockito.mock(DetailAST.class);
//
//            //additional mocking to deal with Dan's Treewalker
//            doReturn(objBlock).when(ast).findFirstToken(anyInt()); // mock ObjBlock creation
//            doReturn(child).when(objBlock).getFirstChild(); // Mock Child creation
//            doReturn(GrandChild).when(child).getFirstChild(); //mock the great great great granchild
//            doReturn(GreatGrandChild).when(GrandChild).getFirstChild(); //mock the great great great granchild
//
//            doReturn(textAST).when(child).findFirstToken(TokenTypes.IDENT); //mock getting text from child
//            doReturn(GrandChild).when(child).findFirstToken(TokenTypes.SLIST); //mock getting operators from method definition
//
//            doReturn(1).when(child).getChildCount(); //mock 1 child
//            doReturn(1).when(GrandChild).getChildCount(); //stop the loop when reaching here
//            doReturn(0).when(GreatGrandChild).getChildCount(); //stop the loop when reaching here
//
//            doReturn(TokenTypes.METHOD_DEF).when(child).getType(); // operator
//            doReturn(type).when(GrandChild).getType(); // operator
//            doReturn(1).when(GrandChild).getChildCount(type); //stop the loop when reaching here
//            doReturn(true).when(objBlock).branchContains(type); // need to mock some other attributes for Dan's treewalker
//            test.visitToken(ast); // This should re-count the grand-child for operands too since it is not re-assigned.
//        }
//
//        // Should count 45 unique operators
//        // There should then be 90 total operators as each should be counted twice, once in the treewalker, and another time in the countOperators function.
//		// It seems that not all are getting counted in the treewalker as unique. But all that enter the countOperators function get counted correctly.
//
//        test.finishTree(ast1);
//
//        int ops = test.getOperatorsCount();
//        int ands = test.getOperandsCount();
//        int uops = test.getUniqueOperators();
//        int uands = test.getUniqueOperands();
//
//        assertEquals(OurOperators.length, test.getUniqueOperators()); // does not catch all the different kinds of operators!
//        assertEquals(90, test.getOperatorsCount()); // This is counting all the operators, just not all the unique ones
//    }

	@Test
	public void testVisitTheirOperators() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(2).when(test).getLOC(); // need to mock the lines of code cause its run each time.
		DetailAST ast1 = PowerMockito.mock(DetailAST.class); //parent

		test.beginTree(ast1); // begin the tree

		for (int type : TheirOperators) {

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

			doReturn(TokenTypes.METHOD_DEF).when(child).getType(); // operator
			doReturn(type).when(GrandChild).getType(); // operator
			doReturn(1).when(GrandChild).getChildCount(type); //stop the loop when reaching here
			doReturn(true).when(objBlock).branchContains(type); // need to mock some other attributes for Dan's treewalker
			test.visitToken(ast); // This should re-count the grand-child for operands too since it is not re-assigned.
		}

		// Should count 45 unique operators
		// There should then be 90 total operators as each should be counted twice, once in the treewalker, and another time in the countOperators function.
		// It seems that not all are getting counted in the treewalker as unique. But all that enter the countOperators function get counted correctly.

		test.finishTree(ast1);

		int ops = test.getOperatorsCount();
		int ands = test.getOperandsCount();
		int uops = test.getUniqueOperators();
		int uands = test.getUniqueOperands();


		assertEquals(100, test.getOperatorsCount()); // This is counting all the operators, just not all the unique ones
		assertEquals(TheirOperators.length, test.getUniqueOperators()); // does not catch all the different kinds of operators!
        assertEquals(0, test.getOperandsCount());
        assertEquals(0, test.getUniqueOperands());
	}

    @Test
    public void testVisitTheirOperators2() {
        HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
        doReturn(2).when(test).getLOC(); // need to mock the lines of code cause its run each time.
        DetailAST ast1 = PowerMockito.mock(DetailAST.class); //parent

        test.beginTree(ast1); // begin the tree

        for (int type : TheirOperators) {

            DetailAST ast = PowerMockito.mock(DetailAST.class); //parent
            DetailAST objBlock = PowerMockito.mock(DetailAST.class); //child
            DetailAST child = PowerMockito.mock(DetailAST.class); //grandchild
            DetailAST GrandChild = PowerMockito.mock(DetailAST.class); //grandchild
            DetailAST GreatGrandChild = PowerMockito.mock(DetailAST.class); //grandchild

            DetailAST bro1 = PowerMockito.mock(DetailAST.class); //grandchild
            DetailAST bro2 = PowerMockito.mock(DetailAST.class); //grandchild
            DetailAST bro3 = PowerMockito.mock(DetailAST.class); //grandchild

            DetailAST textAST = PowerMockito.mock(DetailAST.class);

            //additional mocking to deal with Dan's Treewalker
            doReturn(objBlock).when(ast).findFirstToken(anyInt()); // mock ObjBlock creation
            doReturn(child).when(objBlock).getFirstChild(); // Mock Child creation
            doReturn(GrandChild).when(child).getFirstChild(); //mock the great great great granchild
            doReturn(GreatGrandChild).when(GrandChild).getFirstChild(); //mock the great great great granchild

//            doReturn(bro1).when(GrandChild).getNextSibling();
            doReturn(bro1).when(GreatGrandChild).getNextSibling();
//            doReturn(bro1).when(child).getNextSibling();
            doReturn(bro2).when(bro1).getNextSibling();
            doReturn(bro3).when(bro2).getNextSibling();

            doReturn(textAST).when(child).findFirstToken(TokenTypes.IDENT); //mock getting text from child
            doReturn(GrandChild).when(child).findFirstToken(TokenTypes.SLIST); //mock getting operators from method definition

            doReturn(1).when(child).getChildCount(); //mock 1 child
            doReturn(1).when(GrandChild).getChildCount(); //stop the loop when reaching here
            doReturn(1).when(bro1).getChildCount();
            doReturn(1).when(bro2).getChildCount();
            doReturn(1).when(bro3).getChildCount();
            doReturn(0).when(GreatGrandChild).getChildCount(); //stop the loop when reaching here

            doReturn(TokenTypes.METHOD_DEF).when(child).getType(); // operator
            doReturn(type).when(GrandChild).getType(); // operator
            doReturn(1).when(GrandChild).getChildCount(type); //stop the loop when reaching here
            doReturn(1).when(bro1).getChildCount(type);
            doReturn(1).when(bro2).getChildCount(type);
            doReturn(1).when(bro3).getChildCount(type);
            doReturn(true).when(objBlock).branchContains(type); // need to mock some other attributes for Dan's treewalker
            test.visitToken(ast); // This should re-count the grand-child for operands too since it is not re-assigned.
        }

        // Should count 45 unique operators
        // There should then be 90 total operators as each should be counted twice, once in the treewalker, and another time in the countOperators function.
        // It seems that not all are getting counted in the treewalker as unique. But all that enter the countOperators function get counted correctly.

        test.finishTree(ast1);

        int ops = test.getOperatorsCount();
        int ands = test.getOperandsCount();
        int uops = test.getUniqueOperators();
        int uands = test.getUniqueOperands();


        // getting all the children counts each operator 4 times, and then an additional time in the treewalker
        assertEquals(250, test.getOperatorsCount()); // This is counting all the operators, just not all the unique ones
        assertEquals(TheirOperators.length, test.getUniqueOperators()); // does not catch all the different kinds of operators!
        assertEquals(0, test.getOperandsCount());
        assertEquals(0, test.getUniqueOperands());
    }

    @Test
    public void testVisit() {
        HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
        doReturn(2).when(test).getLOC(); // need to mock the lines of code cause its run each time.
        DetailAST ast1 = PowerMockito.mock(DetailAST.class); //parent

        test.beginTree(ast1); // begin the tree

        for (int type : operandTokens) {

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
        assertEquals(0, test.getOperatorsCount());
        assertEquals(0, test.getUniqueOperators());
    }
}
