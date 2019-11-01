/*
 * This class is responsible for building a detailASTree for a given file input.
 * Included is the runtTree function that will execute the check on a given file.
 */

package blackBoxTests;

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
	
	/**
	 * 
	 * @param fp Path to the input .java file to run the check on.
	 * @param checkToTest AbstractCheck type to execute on the given file.
	 */
	public TestCheckEngine(String fp, AbstractCheck checkToTest) {
		setFilePath(fp); //set the internal filepath
		setCheck(checkToTest); // set the check that will be tested
		
		try {
			ast = buildAST(fp); //try to build the AST from the source file
		} catch (CheckstyleException e1) {
			System.out.println("CheckstyleException!");
		} catch (IOException e2) {
			System.out.println("IOException!");
		}
	}
	
	// setter for the filepath
	public void setFilePath(String fp) {
		filePath = fp;
	}
	
	// setter for the check
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
	
	//Execute the check on the tree
	public void runTree() throws CheckstyleException {
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		check.beginTree(ast);
		visitTokens(ast);
		check.finishTree(ast);
	}

	// calls visitToken and leaveToken on all tokens within the ASTree.
	private void visitTokens(DetailAST root) {
		List<Integer> tokens = IntStream.of(check.getAcceptableTokens()).boxed().collect(Collectors.toList()); //convert the array of acceptable tokens to a list
		DetailAST curNode = root; //set current node to the root
        while (curNode != null) { //while there are still nodes in the tree
        	if (tokens.contains(curNode.getType())) //if the node selected is included in the acceptable Tokens of the check
        		check.visitToken(curNode);
            DetailAST toVisit = curNode.getFirstChild(); //check if there is a node after this one
            while (curNode != null && toVisit == null) { //find children nodes
            	if (tokens.contains(curNode.getType())) //if the node selected is included in the acceptable Tokens of the check
            		check.leaveToken(curNode);
                toVisit = curNode.getNextSibling(); //check next node
                curNode = curNode.getParent(); // reset current node
            }
            curNode = toVisit; //move on to the next node
        }
	}
}
