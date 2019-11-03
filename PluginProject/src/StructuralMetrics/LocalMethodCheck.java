/*
* Description: Counts the number of local methods in a given test file.
*/
package StructuralMetrics;


import com.puppycrawl.tools.checkstyle.api.*;

public class LocalMethodCheck extends AbstractCheck{

	private int localMethodCount;
	private final String CATCH_MSG = "Local method count: ";
	
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.METHOD_CALL};
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.METHOD_CALL};
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	//The token is a method call 
	public void visitToken(DetailAST aAST) {
		//check if the first token is a dot
		if(aAST.findFirstToken(TokenTypes.DOT) == null) {
			//not a dot so this is a local method call
			localMethodCount++;	
		} 
//		else {
//			DetailAST ast = aAST.findFirstToken(TokenTypes.DOT);
//			ast = ast.getParent();
//			if (String.equals(ast.getTokenNames()[0], "")
//		}
	}
	
	//calls begin tree to initialize count variable
	public void beginTree(DetailAST rootAST) {
		localMethodCount = 0;
	}
	
	//prints the log message
	public void finishTree(DetailAST rootAST) {
		try {
			log(rootAST, CATCH_MSG + localMethodCount);
		}
		catch (NullPointerException e) {
			System.out.println("Can't run log unless called from treewalker!");
		}
	}
	
	//returns the count variable
	public int getCount() {
		return localMethodCount;
	}

}
