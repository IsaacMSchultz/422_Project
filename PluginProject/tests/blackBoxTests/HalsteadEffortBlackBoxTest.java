package blackBoxTests;

import static org.junit.Assert.*;

import org.junit.Test;

import StructuralMetrics.HalsteadDifficulty;
import StructuralMetrics.HalsteadEffort;
import StructuralMetrics.HalsteadVolume;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import blackBoxTests.TestCheckEngine;

public class HalsteadEffortBlackBoxTest {
	// Halstead Effort is the difficulty multiplied by the volume.

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\HalsteadMaintainabilityIndex\\MaintainabilityIndex1.java";
 
	@Test
	public void test() {
		HalsteadEffort check = new HalsteadEffort();

		TestCheckEngine t = new TestCheckEngine(filePath, check);
		try {
			t.runTree(); // try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertEquals(2795.9, check.getHalsteadEffort(), 0.5);
	}
}
