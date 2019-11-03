package blackBoxTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaintainabilityBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\MaintainabilityIndexCases";
	@Test
	public void test() {
		MaintainabilityIndex check = new MaintainabilityIndex();
		CyclomaticComplexityCounter cycloCheck = new CyclomaticComplexityCounter();
		HalsteadVolume volume = new HalsteadVolume();
		
		TestCheckEngine t = new TestCheckEngine(filePath, check);
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(114.37, check.MaintainabilityIndex()); //determine if execution created the correct value
		
		//check that component values are correct
		TestCheckEngine t = new TestCheckEngine(filePath, cycloCheck);
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, cycloCheck.cyclomaticComplexity());
		
		TestCheckEngine t = new TestCheckEngine(filePath, volume);
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(8, volume.halsteadVolume());
	}
	
	filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\MaintainabilityCases2";
	
	@Test
	public void test2() {
		MaintainabilityIndex check = new MaintainabilityIndex();
		CyclomaticComplexityCounter cycloCheck = new CyclomaticComplexityCounter();
		HalsteadVolume volume = new HalsteadVolume();
		
		TestCheckEngine t = new TestCheckEngine(filePath, check);
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(97.48, check.MaintainabilityIndex()); //determine if execution created the correct value
		
		//check that component values are correct
		TestCheckEngine t = new TestCheckEngine(filePath, cycloCheck);
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(4, cycloCheck.cyclomaticComplexity());
		
		TestCheckEngine t = new TestCheckEngine(filePath, volume);
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(8, volume.halsteadVolume());
	}
		}

}
