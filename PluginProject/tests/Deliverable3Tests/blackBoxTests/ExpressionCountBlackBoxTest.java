package Deliverable3Tests.blackBoxTests;

import StructuralMetrics.ExpressionCountCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExpressionCountBlackBoxTest {
	
	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\ExpressionCountCheck\\ExpressionCountCheck";

	@Test
	public void test1() {
		ExpressionCountCheck c = new ExpressionCountCheck(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(7, c.getCount()); //determine if execution created the correct value
    }

    @Test
	public void test2() {
		ExpressionCountCheck c = new ExpressionCountCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(0, c.getCount()); //determine if execution created the correct value
	}
}
