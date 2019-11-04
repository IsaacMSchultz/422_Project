package blackBoxTests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import StructuralMetrics.HalsteadDifficulty;

public class HalsteadDifficultyBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\HalsteadDifficulty";
	
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

		assertEquals(10.8, check.getHalsteadDifficulty(), 0.1);
	}

}
