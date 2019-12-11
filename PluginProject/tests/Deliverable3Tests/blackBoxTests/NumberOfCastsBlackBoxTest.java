package Deliverable3Tests.blackBoxTests;

import TeamRebecca.CastsCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberOfCastsBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\NumberOfCastsCheck\\NumberOfCastsCheck";

	@Test
	public void test1() {
		CastsCheck c = new CastsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(2, c.getCasts()); //determine if execution created the correct value
	}

	@Test
	public void test2() {
		CastsCheck c = new CastsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(0, c.getCasts()); //determine if execution created the correct value
	}

	@Test
	public void test3() {
		CastsCheck c = new CastsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "3.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(3, c.getCasts()); //determine if execution created the correct value
	}
}