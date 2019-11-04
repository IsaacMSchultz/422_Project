package blackBoxTests;
import static org.junit.Assert.*;

import org.junit.Test;

import blackBoxTests.CheckstyleException;
import blackBoxTests.HalsteadDifficulty;
import blackBoxTests.TestCheckEngine;

public class HalsteadEffortBlackBoxTest {
	//	Halstead Effort is the difficulty multiplied by the volume. 
	
	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\MaintainabilityIndexCases";
	
	@Test
	public void test() {
		HalsteadEffort check = new HalsteadEffort();

		TestCheckEngine t = new TestCheckEngine(filePath, check);
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(2,764.8,check.halsteadEffort(),0.5);
	}
		
		//testing component parts
		@Test
		public void test2() {
			HalsteadDifficulty difficulty = new HalsteadDifficulty();
			TestCheckEngine t = new TestCheckEngine(filePath, check);
			try {
				t.runTree(); //try to execute the check on the whole tree
			} catch (CheckstyleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			assertEquals(10.8, difficulty.halsteadDifficulty(), 0.1);
		}
		
		@Test
		public void test3() {
			
			HalsteadVolume volume = new HalsteadVolume();
			TestCheckEngine t = new TestCheckEngine(filePath, volume);
			try {
				t.runTree(); //try to execute the check on the whole tree
			} catch (CheckstyleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			assertEquals(256, volume.halsteadVolume());
		}
	}
}
