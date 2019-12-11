package Deliverable2Tests.blackBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import StructuralMetrics.MaintainabilityIndex;

public class MaintainabilityIndexBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\HalsteadMaintainabilityIndex\\MaintainabilityIndex";

	@Test
	public void test() {
		MaintainabilityIndex check = new MaintainabilityIndex();

		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", check);
		try {
			t.runTree(); // try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

		assertEquals(114.37, check.getMaintainabilityIndex(), 0.8); // high error since LOC changes sometimes because of linux/windows file endings
	}

	@Test
	public void test2() {
		MaintainabilityIndex check = new MaintainabilityIndex();

		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", check);
		try {
			t.runTree(); // try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

		assertEquals(97.48, check.getMaintainabilityIndex(), 0.8); // high error since LOC changes sometimes because of linux/windows file endings

	}

}
