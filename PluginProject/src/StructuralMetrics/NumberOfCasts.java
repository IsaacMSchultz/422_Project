package StructuralMetrics;


import com.puppycrawl.tools.checkstyle.api.*;
import java.util.HashMap;

public class NumberOfCasts extends AbstractCheck{
	private int varCount = 0;
	

	private final String CATCH_MSG = "Number of variables: ";
	
	//private final VariableDefCounter varCounter = new VariableDefCounter();
	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.TYPECAST}; 
	}
	
	@Override
    public void visitToken(DetailAST aAST) {		
		log(aAST.getLineNo(), "Number of casts");
    }

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return new int[] {TokenTypes.TYPECAST}; 
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return new int[0];
	}

}

//Might use this to count. - Dont need it because checkstyle should count if its the same log message each time????
class VariableTpyeNameCounter {
	private HashMap variableNamesTypes = new HashMap();
	
}
	
