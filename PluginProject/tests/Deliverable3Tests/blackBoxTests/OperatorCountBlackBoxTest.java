package Deliverable3Tests.blackBoxTests;

import StructuralMetrics.OperatorCountCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperatorCountBlackBoxTest {
	
	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\OperatorCountCheck\\OperatorCountCheck";

	@Test
	public void test1() {
		OperatorCountCheck c = new OperatorCountCheck(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

		assertEquals(76, c.getCount()); //determine if execution created the correct value
	}

	@Test
	public void test2() {
		OperatorCountCheck c = new OperatorCountCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //check to see if it finds 40 unique operators

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

		assertEquals(39, c.getUniqueCount()); //check if it found the 40 unique operators
	}

	@Test
	public void test3() {
		OperatorCountCheck c = new OperatorCountCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); // checks if it finds operators in comments
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

		assertEquals(0, c.getCount()); // assert if there are no operators in comments
	}
}
