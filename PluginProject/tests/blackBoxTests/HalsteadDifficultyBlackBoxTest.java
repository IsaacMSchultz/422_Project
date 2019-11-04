package blackBoxTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class HalsteadDifficultyBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\MaintainabilityIndexCases";
	
	//•	Halstead Difficulty is half of the unique operators 
	//multiplied by the total number of operands, 
	//divided by the number of distinct operators 
	
	@Test
	public void test() {
		HalsteadDifficulty check = new HalsteadDifficulty();

		TestCheckEngine t = new TestCheckEngine(filePath, check);
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(10.8, check.halsteadDifficulty(), 0.5);
	}

}
