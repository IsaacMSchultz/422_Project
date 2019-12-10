package Deliverable3Tests.blackBoxTests;

import TeamRebecca.HalsteadMetricsCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperatorCountBlackBoxTest {
	
	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\OperatorCountCheck\\OperatorCountCheck";

	@Test
	public void totalNumberOfOperands() {
		HalsteadMetricsCheck c = new HalsteadMetricsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

		assertEquals(76, c.getOperatorsCount()); //determine if execution created the correct value
	}

	@Test
	public void all39UniqueOperators() {
		HalsteadMetricsCheck c = new HalsteadMetricsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //check to see if it finds 40 unique operators

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

		assertEquals(39, c.getUniqueOperators()); //check if it found the 39 unique operators
	}

	@Test
	public void operatorsInComments() {
		HalsteadMetricsCheck c = new HalsteadMetricsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); // checks if it finds operators in comments
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

		assertEquals(0, c.getOperatorsCount()); // assert if there are no operators in comments
	}
}
