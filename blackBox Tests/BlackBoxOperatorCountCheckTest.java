package blackBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.LocalMethodCheck;

public class BlackBoxOperatorCountCheck {
	
	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\OperatorCountCheck\\OperatorCountCheck";

	@Test
	public void test1() {
		OperatorCountCheck c = new OperatorCountCheck(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(c.getCount(), 40); //determine if execution created the correct value
	}
	
	@Test
	public void test2() {
		OperatorCountCheck c = new OperatorCountCheck(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(c.getCount(), 0); //determine if execution created the correct value
	}
}
