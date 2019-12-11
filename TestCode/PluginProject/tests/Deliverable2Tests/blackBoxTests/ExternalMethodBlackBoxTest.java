package Deliverable2Tests.blackBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import StructuralMetrics.ExternalMethodCheck;

public class ExternalMethodBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\ExternalMethodCheck\\ExternalMethodCheck";

	@Test
	public void test1() {
		ExternalMethodCheck c = new ExternalMethodCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, c.getCount()); //this.  should not count as external method
	}

	@Test
	public void test2() {
		ExternalMethodCheck c = new ExternalMethodCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, c.getCount()); //this.  should not count as external method
	}

	@Test
	public void test3() {
		ExternalMethodCheck c = new ExternalMethodCheck();
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