package blackBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.MaintainabilityIndex;

public class MaintainabilityIndexBlackBoxTest {

	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\MaintainabilityIndexCases";

	@Test
	public void test() {
		MaintainabilityIndex check = new MaintainabilityIndex();

		TestCheckEngine t = new TestCheckEngine(filePath, check);
		try {
			t.runTree(); // try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

		assertEquals(114.37, check.getMaintainabilityIndex(), 0.1); // determine if execution created the correct value
	}

	@Test
	public void test2() {
		MaintainabilityIndex check = new MaintainabilityIndex();

		TestCheckEngine t = new TestCheckEngine(filePath, check);
		try {
			t.runTree(); // try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			e.printStackTrace();
		}

		assertEquals(97.48, check.getMaintainabilityIndex(), 0.1); // determine if execution created the correct value

	}

}
