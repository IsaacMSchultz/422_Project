package blackBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.NumberOfCastsCheck;


public class BlackBoxNumberOfCastsCheckTest {
	
	int[] expectedTokens = {TokenTypes.TYPECAST};
	String filePath = "D:\\Git\\422_Project\\TestCode\\Y_E_E_T.java";

	@Test
	public void testTest() {
		NumberOfCastsCheck c = new NumberOfCastsCheck(); 
		TestCheckEngine t = new TestCheckEngine(filePath, c); //create a tester with filepath, and the check c
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c.getCasts()); //print the result (debug)
		assertEquals(c.getCasts(), 2); //determine if execution created the correct value
	}
}
