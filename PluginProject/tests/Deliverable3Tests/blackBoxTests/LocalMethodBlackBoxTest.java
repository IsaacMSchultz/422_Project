package Deliverable3Tests.blackBoxTests;

import StructuralMetrics.LocalMethodCheck;
import TeamRebecca.LocalMethodsCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocalMethodBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\LocalMethodCheck\\localMethodCheck";

	@Test
	public void test1() {
		LocalMethodsCheck c = new LocalMethodsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(3, c.getLocalMethods()); //determine if execution created the correct value
	}

	@Test
	public void test2() {
		LocalMethodsCheck c = new LocalMethodsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(0, c.getLocalMethods()); //determine if execution created the correct value
	}

	@Test
	public void test3() {
		LocalMethodsCheck c = new LocalMethodsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "3.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(2, c.getLocalMethods()); // this. and super. not covered correctly
	}

	@Test
	public void test4() {
		LocalMethodsCheck c = new LocalMethodsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "4.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(4, c.getLocalMethods()); // this. and super. not covered correctly
	}
}
