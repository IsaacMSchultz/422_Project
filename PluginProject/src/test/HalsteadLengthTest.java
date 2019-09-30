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

import java.util.ArrayList;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import StructuralMetrics.HalsteadLength;
import StructuralMetrics.OperandCounter;
import StructuralMetrics.OperatorCounter;

@RunWith(PowerMockRunner.class)
@PrepareForTest({OperandCounter.class, OperatorCounter.class})
class HalsteadLengthTest {
	
	int[] halfArray1 = { 0, 1 }, halfArray2 = { 2, 3 };
	int[] fullArray = { 0, 1, 2, 3 };

	// Create mocks of the different classes that Halstead Effort depends on.
	@Mock (name = "operatorCount")
	OperatorCounter operatorMock;

	@Mock (name = "operandCount")
	OperandCounter operandMock;
	
//	@Mock (name = "operandTokens")
//	ArrayList<Integer> operandTokens;
//	
//	@Mock (name = "operatorTokens")
//	ArrayList<Integer> operatorTokens;
	
	@InjectMocks
	HalsteadLength injectedTest;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this); // Initialize all the @Mock objects
	}

	@Test
	void testGetDefaultTokens() {
		PowerMockito.when(operatorMock.getDefaultTokens()).thenReturn(halfArray1);
		PowerMockito.when(operandMock.getDefaultTokens()).thenReturn(halfArray2);
		
		assertArrayEquals(fullArray, injectedTest.getDefaultTokens());
	}
//
//	@Test
//	void testGetAcceptableTokens() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetRequiredTokens() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testBeginTreeDetailAST() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testVisitTokenDetailAST() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFinishTreeDetailAST() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetHalsteadLength() {
//		fail("Not yet implemented");
//	}

}
