package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import StructuralMetrics.HalsteadEffort;
import StructuralMetrics.HalsteadDifficulty;
import StructuralMetrics.HalsteadVolume;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HalsteadEffort.class)
public class HalsteadEffortTest {

	int[] halfArray1 = { 0, 1 }, halfArray2 = { 2, 3 };
	int[] fullArray = { 0, 1, 2, 3 };

	int[] expectedTokens = { TokenTypes.DEC, TokenTypes.INC, TokenTypes.LNOT, TokenTypes.POST_DEC, TokenTypes.POST_INC,
			TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
			TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN,
			TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DIV,
			TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.LAND,
			TokenTypes.LE, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD,
			TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.SL,
			TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.QUESTION,
			TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG };

	// We want to mock the functions called on the classes that the tested class
	// depends on.
	private static final HalsteadVolume hv = mock(HalsteadVolume.class);
	private static final HalsteadDifficulty hd = mock(HalsteadDifficulty.class);

	// Set known output to simplify the results of the tests
	@Before
	public void setupTests() {
		when(hv.getDefaultTokens()).thenReturn(halfArray1);
		when(hv.getAcceptableTokens()).thenReturn(halfArray1);
//		when(hv.getRequiredTokens()).thenReturn(halfArray1);

		when(hd.getDefaultTokens()).thenReturn(halfArray2);
		when(hd.getAcceptableTokens()).thenReturn(halfArray2);
//		when(hd.getRequiredTokens()).thenReturn(halfArray2);
	}

	@Test
	public void testGetDefaultTokens() {
		HalsteadEffort spy = PowerMockito.spy(new HalsteadEffort());
		
		when(spy, 

		assertArrayEquals(fullArray, test.getDefaultTokens());
	}

//	@Test
//	public void testGetAcceptableTokens() {
//		//HalsteadEffort test = new HalsteadEffort();
//
//		doReturn(halfArray1).when(hdMock.getAcceptableTokens());
//		doReturn(halfArray2).when(hvMock.getAcceptableTokens());
//
//		assertArrayEquals(injectedTest.getAcceptableTokens(), fullArray);
//	}
//
//	@Test
//	public void testGetRequiredTokens() {
//		//HalsteadEffort test = new HalsteadEffort();
//
//		doReturn(halfArray1).when(hdMock.getRequiredTokens());
//		doReturn(halfArray2).when(hvMock.getRequiredTokens());
//
//		assertArrayEquals(injectedTest.getRequiredTokens(), fullArray);
//	}
//
//// Does this need to be tested? If so how will it be done since the only things that change are in the depended classes!
////	@Test
////	public void testBeginTreeDetailAST() {
////		fail("Not yet implemented");
////	}
//
////	@Test
////	public void testVisitTokenDetailAST() {
////		//HalsteadEffort testSpy = spy(new HalsteadEffort());
////		
////		doReturn(true).when(arrList.contains(ast)); //always return true when checking if something is contained within an arrayList
////		
////		injectedTest.visitToken(ast);
////	}
//
//	@Test
//	 public void testFinishTreeDetailAST() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetHalsteadEffort() {
//		fail("Not yet implemented");
//	}

}
