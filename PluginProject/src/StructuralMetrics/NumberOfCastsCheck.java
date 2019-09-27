package StructuralMetrics;


import com.puppycrawl.tools.checkstyle.TreeWalker;
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
		log(rootAST, "Number of casts: {0}. Also, the Cyclomatic Complexity is: {1}", casts, test.getCycles());
	}
	
	@Override
	public void beginTree(DetailAST rootAST)
	{
		casts = 0;
		TreeWalker walker = new TreeWalker();
		//walker.
//		this.
		
		//Trying to run the whole CyclomaticComplexityCounter to get the number as a test. 
		// I think the treewalker is what needs to be used???
		test = new CyclomaticComplexityCounter();
		test.setFileContents(this.getFileContents());
		test.init();
		
		System.out.println("begin cast");
	}
	
	public int getCasts()
	{
		return casts;
	}

}
