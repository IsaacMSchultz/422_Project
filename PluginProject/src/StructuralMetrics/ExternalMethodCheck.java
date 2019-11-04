/*
* Description: Counts the number of external methods in a given test file. 
*/
package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.*;

public class ExternalMethodCheck extends AbstractCheck {

	private int extMethodCount;
	private final String CATCH_MSG = "External method count: ";

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.METHOD_CALL };
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.METHOD_CALL };
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	//When a method call is found
	public void visitToken(DetailAST aAST) {
		//check if the next token is a dot
		if (aAST.findFirstToken(TokenTypes.DOT) != null) {
			//the next token is a dot which means this is a external method call.
			extMethodCount++;
		}
	}

	//calls begin tree to initialize count variable
	public void beginTree(DetailAST rootAST) {
		extMethodCount = 0;
	}

	//logs the external method count
	public void finishTree(DetailAST rootAST) {
		try {
			log(rootAST, CATCH_MSG + extMethodCount);
		} catch (NullPointerException e) {
			System.out.println("Can't run log unless called from treewalker!");
		}
	}

	//returns the external method count
	public int getCount() {
		return extMethodCount;
	}

}
