package blackBoxTests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import StructuralMetrics.HalsteadDifficulty;

public class HalsteadDifficultyBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\HalsteadMaintainabilityIndex\\MaintainabilityIndex1.java";
	//�	Halstead Difficulty is half of the unique operators 
	//multiplied by the total number of operands, 
	//divided by the number of distinct operators 
	
	@Test
	public void test() {
		HalsteadDifficulty check = new HalsteadDifficulty();

		TestCheckEngine t = new TestCheckEngine(filePath, check);
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (6 / 2) * ( 38 / 11) = 
		assertEquals(10.36, check.getHalsteadDifficulty(), 0.1); //does not calculate correctly, off by a small margin because we do not properyly
	}

}
