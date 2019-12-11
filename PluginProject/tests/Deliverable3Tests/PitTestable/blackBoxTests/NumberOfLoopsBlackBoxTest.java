package Deliverable3Tests.PitTestable.blackBoxTests;

import TeamRebecca.LoopsCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

;

public class NumberOfLoopsBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\NumberOfLoopsCheck\\NumberOfLoopsCheck";

	@Test
	public void test1() {
		LoopsCheck c = new LoopsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(3, c.getLoopCount()); //determine if execution created the correct value
	}

	@Test
	public void test2() {
		LoopsCheck c = new LoopsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, c.getLoopCount()); //determine if execution created the correct value
	}

	@Test
	public void test3() {
		LoopsCheck c = new LoopsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "3.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, c.getLoopCount()); //determine if execution created the correct value
	}

	@Test
	public void test4() {
		LoopsCheck c = new LoopsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "4.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, c.getLoopCount()); //determine if execution created the correct value
	}
}
