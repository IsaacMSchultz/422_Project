/*
 * This class is responsible for building a detailASTree for a given file input.
 * There are functions provided to assist with running tests.
 */

package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class TestCheckEngine {

	private String filePath; //generic reference to where the folder containing all our test files is located
	private DetailAST ast;
	private AbstractCheck check;
	
	public TestCheckEngine(String fp, AbstractCheck checkToTest) {
		setFilePath(fp);
		setCheck(checkToTest);
		
		try {
			ast = buildAST(fp);
		} catch (CheckstyleException e1) {
			System.out.println("CheckstyleException!");
		} catch (IOException e2) {
			System.out.println("IOException!");
		}
	}
	
	public void setFilePath(String fp) {
		filePath = fp;
	}
	
	public void setCheck(AbstractCheck checkToTest) {
		check = checkToTest;
	}
	
	// Using the base filePath, this function creates a detailASTree by parsing the file passed in fileName
	public DetailAST buildAST(String fileName) throws IOException, CheckstyleException {
		File f = new File(fileName);
		FileText ft = new FileText(f, "UTF-8");
		FileContents fc = new FileContents(ft);
		return JavaParser.parse(fc);
	}
	
	public void runTree() throws CheckstyleException {
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		check.beginTree(ast);
		visitTokens(ast);
		check.finishTree(ast);
	}

	private void visitTokens(DetailAST root) {
		List<Integer> tokens = IntStream.of(check.getAcceptableTokens()).boxed().collect(Collectors.toList());
		DetailAST curNode = root;
        while (curNode != null) {
        	if (tokens.contains(curNode.getType()))
        		check.visitToken(curNode);
            DetailAST toVisit = curNode.getFirstChild();
            while (curNode != null && toVisit == null) {
            	check.leaveToken(curNode);
                toVisit = curNode.getNextSibling();
                curNode = curNode.getParent();
            }
            curNode = toVisit;
        }
	}
}
