package Deliverable2Tests.blackBoxTests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import StructuralMetrics.HalsteadVolume;

public class HalsteadVolumeBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\HalsteadVolume\\HalsteadVolumeCases.java";
	@Test
	public void test() {
		HalsteadVolume check = new HalsteadVolume();
		
		TestCheckEngine t = new TestCheckEngine(filePath, check);
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(72.90827, check.getHalsteadVolume(), 0.1);
	}

}
