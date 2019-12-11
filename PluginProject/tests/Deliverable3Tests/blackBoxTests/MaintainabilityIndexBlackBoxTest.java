package Deliverable3Tests.blackBoxTests;

import TeamRebecca.HalsteadMetricsCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaintainabilityIndexBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\HalsteadMaintainabilityIndex\\MaintainabilityIndex";

	@Test
	public void test() {
		HalsteadMetricsCheck check = new HalsteadMetricsCheck();

		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", check);
		try {
			t.runTree(); // try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

		assertEquals(64.37, check.getMaintainabilityIndex(), 0.8); // high error since LOC changes sometimes because of linux/windows file endings
	}

	@Test
	public void test2() {
		HalsteadMetricsCheck check = new HalsteadMetricsCheck();

		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", check);
		try {
			t.runTree(); // try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

//		assertEquals(32, check.getLOC());
//		assertEquals(3, check.getCyclomaticComplexity());
//		assertEquals(1, check.getHalsteadLength());
		assertEquals(47.02, check.getMaintainabilityIndex(), 0.8); // high error since LOC changes sometimes because of linux/windows file endings

	}

}
