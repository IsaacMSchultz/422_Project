package blackBoxTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class HalsteadVolumeBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\HalsteadVolumeCases";
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
		
		assertEquals(60.22857502, check.halsteadVolume());
	}

}
