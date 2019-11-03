/*
* Description: Counts the number of variables in a given test file.
*/
package StructuralMetrics;


import com.puppycrawl.tools.checkstyle.api.*;

public class VariableCountCheck extends AbstractCheck{
	private int varCount;

	private final String CATCH_MSG = "Number of variables: ";

	@Override
	public int[] getDefaultTokens() {
		
		return new int[] {TokenTypes.VARIABLE_DEF}; 
	}
	
	//increment variable count
	@Override
    public void visitToken(DetailAST aAST) {
		++varCount;
    }

	@Override
	public int[] getAcceptableTokens() { 
		return new int[] {TokenTypes.VARIABLE_DEF}; 
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	//calls begin tree to initialize count variable
	public void beginTree(DetailAST rootAST) {
		varCount = 0;
	}
	
	//logs the number of variables
	@Override
	public void finishTree(DetailAST rootAST) {
		log(rootAST, CATCH_MSG + varCount);
	}
	
	//returns the variable count
	public int getCount() {
		return varCount;
	}
	//implementing anti pattern duplicate code
	public int getVariableCount() {
		return varCount; 
	}
	
	//implementing anti pattern duplicate code
	public int getActualCount() {
		return varCount;
	}

}
	
