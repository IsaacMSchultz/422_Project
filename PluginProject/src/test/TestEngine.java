/*
 * This class is responsible for building a detailASTree for a given file input.
 * There are functions provided to assist with running tests.
 */

package test;

import java.io.File;
import java.io.IOException;

import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class TestEngine {

	private String baseFilePath; //generic reference to where the folder containing all our test files is located
	
	public TestEngine(String fp) {
		setBaseFilePath(fp);
	}
	
	public void setBaseFilePath(String fp) {
		baseFilePath = fp;
	}
	
	// Using the base filePath, this function creates a detailASTree by parsing the file passed in fileName
	private DetailAST buildAST(String fileName) throws IOException, CheckstyleException {
		File f = new File(baseFilePath + fileName);
		FileText ft = new FileText(f, "UTF-8");
		FileContents fc = new FileContents(ft);
		return JavaParser.parse(fc);
	}
}
