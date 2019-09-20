package StructuralMetrics;


import com.puppycrawl.tools.checkstyle.api.*;
import java.util.HashMap;

public class NumberOfCastsCheck extends AbstractCheck{
	
	int casts;
	CyclomaticComplexityCounter test;

	@Override
	public int[] getDefaultTokens() {		
		//test.beginTree(rootAST);
		return new int[] {TokenTypes.TYPECAST}; 
	}
	
	@Override
    public void visitToken(DetailAST aAST) {		
		casts++;
//		System.out.println("Cast found");
    }

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.TYPECAST}; 
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	@Override
	public void finishTree(DetailAST rootAST)
	{
		log(rootAST, "Number of casts: {0}. Also, the Cyclomatic Complexity is: ", casts, test.getCycles());
	}
	
	@Override
	public void beginTree(DetailAST rootAST)
	{
		casts = 0;
		test = new CyclomaticComplexityCounter();
	}
	
	public int getCasts()
	{
		return casts;
	}

}
