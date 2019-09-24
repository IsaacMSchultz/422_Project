package StructuralMetrics;


import com.puppycrawl.tools.checkstyle.api.*;

public class VariableDef extends AbstractCheck{
	private int varCount = 0;
	

	private final String CATCH_MSG = "Number of variables: ";
	
	//private final VariableDefCounter varCounter = new VariableDefCounter();
	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.VARIABLE_DEF}; 
	}
	
	@Override
    public void visitToken(DetailAST aAST) {
		varCount++;
        //System.out.println("This ran!" + varCounter.getVarCount());
		reportStyleError(aAST, varCount);
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
	
	private void reportStyleError(DetailAST aAST, int varCount) {
        log(aAST.getLineNo(), CATCH_MSG + String.valueOf(varCount));
    }

}

class VariableDefCounter {
	private int varCount = 0;
	
	public boolean addVarCount() {
		varCount++;
		return true;
	}
	
	public int getVarCount() {
		return varCount;
	}
}
	
