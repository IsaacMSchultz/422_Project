package Deliverable3Tests.blackBoxTests;

import TeamRebecca.HalsteadMetricsCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperandCountBlackBoxTest {
	
	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\OperandCountCheck\\OperandCountCheck";

	@Test
	public void test1() {
		HalsteadMetricsCheck c = new HalsteadMetricsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(116, c.getOperandsCount()); //Their code might not be counting operators correctly.
//		assertEquals(114, c.getOperandsCount()); //Their code might not be counting operators correctly.
	}

	@Test
	public void test2() {
		HalsteadMetricsCheck c = new HalsteadMetricsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(45, c.getUniqueOperands()); // Their code says there are way too many unique operands. There should only be 45 I think it is counting the class, and the 36 lines that contain a number as unique. I feel this is somewhat accurate.
		//assertEquals(81, c.getUniqueOperands()); // Their code says there are way too many unique operands. There should only be 45 I think it is counting the class, and the 36 lines that contain a number as unique. I feel this is somewhat accurate.
	}
	
	@Test
	public void test3() {
		HalsteadMetricsCheck c = new HalsteadMetricsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(0, c.getOperandsCount()); //counts 2 from class name and method def
	}

	@Test
	public void test4() {
		HalsteadMetricsCheck c = new HalsteadMetricsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(0, c.getUniqueOperands()); //Found a bug! Since operand names are the same they are only counted as one unique operand
	}
}
