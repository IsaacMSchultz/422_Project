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
import StructuralMetrics.MaxNumLoopsCheck;

public class MaxNumLoopsTest 
{
	int[] expectedTokens = { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO };
	
	@Test
    public void testGetDefaultTokens() {
        MaxNumLoopsCheck test = new MaxNumLoopsCheck();

        assertArrayEquals(expectedTokens, test.getDefaultTokens());
    }

    @Test
    public void testGetAcceptableTokens() {
    	MaxNumLoopsCheck test = new MaxNumLoopsCheck();

        assertArrayEquals(expectedTokens, test.getAcceptableTokens());
    }

    @Test
    public void testGetRequiredTokens() {
    	MaxNumLoopsCheck test = new MaxNumLoopsCheck();

        assertArrayEquals(expectedTokens, test.getRequiredTokens());
    }
    
    @Test
    public void testCountCommentsCheck() {
    	MaxNumLoopsCheck test = new MaxNumLoopsCheck();
        DetailAST ast = PowerMockito.mock(DetailAST.class);
        
        test.beginTree(ast); //begin the tree
        
        assertEquals(0, test.getLoopCount());
        
        doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(ast).getType(); //operand        
        test.visitToken(ast);
        
        assertEquals(1, test.getLoopCount());
        
        doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(ast).getType(); //operator    
        test.visitToken(ast);
        
        assertEquals(2, test.getLoopCount());
        
        test.finishTree(ast);
        
        
    }
}