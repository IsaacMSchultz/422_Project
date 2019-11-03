package blackBoxTests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import StructuralMetrics.HalsteadVocabulary;

public class HalsteadVocabularyBlackBoxTest {
	
	String filePath = System.getProperty("user.dir") + "\\BlackBoxTestCases\\HalsteadVocabulary\\HalsteadVocabulary";

	@Test
	public void test1() {
		HalsteadVocabulary c = new HalsteadVocabulary(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(15, c.getUniqueOperatorCount()); //determine if execution created the correct value
	}
	
	@Test
	public void test2() {
		HalsteadVocabulary c = new HalsteadVocabulary(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "1.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(3, c.getUniqueOperandCount()); //determine if execution created the correct value
	}

	@Test
	public void test3() {
		HalsteadVocabulary c = new HalsteadVocabulary(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(0, c.getUniqueOperandCount()); //determine if execution created the correct value
	}

	@Test
	public void test4() {
		HalsteadVocabulary c = new HalsteadVocabulary(); 
		TestCheckEngine t = new TestCheckEngine(filePath + "2.java", c); //create a tester with filepath, and the check c
		
		try {
			t.runTree(); //try to execute the check on the whole tree
		} catch (CheckstyleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(0, c.getUniqueOperatorCount()); //determine if execution created the correct value
	}
}
