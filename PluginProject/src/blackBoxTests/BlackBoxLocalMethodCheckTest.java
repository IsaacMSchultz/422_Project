package blackBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.LocalMethodCheck;

public class BlackBoxLocalMethodCheckTest {
	
	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\LocalMethodCheck\\localMethodCheck";

	@Test
	public void test1() {

		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		LocalMethodCheck c = new LocalMethodCheck(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(c.getCount()); //print the result (debug)
		assertEquals(c.getCount(), 3); //determine if execution created the correct value
	}
}
