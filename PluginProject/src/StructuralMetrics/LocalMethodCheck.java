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
	}
	
	//calls begin tree to initialize count variable
	public void beginTree(DetailAST rootAST) {
		localMethodCount = 0;
	}
	
	//prints the log message
	public void finishTree(DetailAST rootAST) {
		log(rootAST, CATCH_MSG + localMethodCount);
	}
	
	//returns the count variable
	public int getCount() {
		return localMethodCount;
	}

}
