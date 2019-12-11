package Deliverable3Tests.blackBoxTests;

import TeamRebecca.HalsteadMetricsCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HalsteadVocabularyBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\HalsteadVocabulary\\HalsteadVocabulary";

	@Test
	public void test1() {
		HalsteadMetricsCheck c = new HalsteadMetricsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(15, c.getUniqueOperators()); //doesnt catch parenthesis being operators
	}

	@Test
	public void test2() {
		HalsteadMetricsCheck c = new HalsteadMetricsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(5, c.getUniqueOperands()); // Counts unique oprands by name, so even if they are different types (class vs constructor) they are counted as one unique operand
	}

	@Test
	public void test3() {
		HalsteadMetricsCheck c = new HalsteadMetricsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, c.getUniqueOperands());
	}

	@Test
	public void test4() {
		HalsteadMetricsCheck c = new HalsteadMetricsCheck();
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c

		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, c.getUniqueOperators());
	}
}
