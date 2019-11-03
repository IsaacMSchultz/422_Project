package blackBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.LocalMethodCheck;
import StructuralMetrics.VariableCountCheck;

public class VariableCountBlackBoxTest {
	
	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\VariableCountCheck\\VariableCountCheck";

	@Test
	public void test1() {
		VariableCountCheck c = new VariableCountCheck(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(9, c.getCount()); //determine if execution created the correct value
	}
	
	@Test
	public void test2() {
		VariableCountCheck c = new VariableCountCheck(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(0, c.getCount()); //determine if execution created the correct value
	}
	
	@Test
	public void test3() {
		VariableCountCheck c = new VariableCountCheck(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "3.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(0, c.getCount()); //determine if execution created the correct value
	}
}
