package Deliverable2Tests.blackBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import StructuralMetrics.HalsteadLength;

public class HalsteadLengthBlackBoxTest {
	
	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\HalsteadLength\\HalsteadLength";

	@Test
	public void test1() {
		HalsteadLength c = new HalsteadLength(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(102, c.getOperandCount()); //BUG: does not catch "this.that" as two operands
	}

	@Test
	public void test2() {
		HalsteadLength c = new HalsteadLength(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(70, c.getOperatorCount()); //BUG: does not count {} or () as operators when it should
	}
	
	@Test
	public void test3() {
		HalsteadLength c = new HalsteadLength(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(2, c.getOperandCount()); //determine if execution created the correct value
	}

	@Test
	public void test4() {
		HalsteadLength c = new HalsteadLength(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(0, c.getOperatorCount()); //determine if execution created the correct value
	}
}
