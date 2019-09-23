package testPlugin;


import com.puppycrawl.tools.checkstyle.api.*;

public class VariableCountCheck extends AbstractCheck{
	private int varCount;

	private final String CATCH_MSG = "Number of variables: ";
	
	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.VARIABLE_DEF}; 
	}
	
	@Override
    public void visitToken(DetailAST aAST) {
		++varCount;
    }

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.VARIABLE_DEF}; 
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return new int[0];
	}
	
	public void beginTree(DetailAST rootAST) {
		varCount = 0;
	}
	
	@Override
	public void finishTree(DetailAST rootAST) {
		log(rootAST, CATCH_MSG + varCount);
	}

}
	
