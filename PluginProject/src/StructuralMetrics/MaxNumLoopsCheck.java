package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class MaxNumLoopsCheck extends AbstractCheck 
{

  private int max = 1; 
  int loopCount;
  
  public void beginTree(DetailAST rootAST) {
	  loopCount = 0;
  }
  
  public void finishTree(DetailAST rootAST) {	  
  }

  @Override
  public int[] getAcceptableTokens() {
      return getRequiredTokens();
  }
  @Override
  public boolean isCommentNodesRequired() {
	  return true;
  }

  @Override
  public int[] getRequiredTokens() {
      return new int[] {
          TokenTypes.LITERAL_FOR,
          TokenTypes.LITERAL_WHILE,          
      };
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE };
  }

  public void setMax(int limit) {
    max = limit;
  }

  @Override
  public void visitToken(DetailAST ast) {	
	  
	switch (ast.getType())
	{
	case TokenTypes.LITERAL_FOR:
		loopCount++;
		if (loopCount > max) 
		{
	   	 log(ast.getLineNo(), "Max number of loops exceeded", max);
		}
		break;
		
	case TokenTypes.LITERAL_WHILE:
		loopCount++;
		loopCount += ast.getChildCount();
		if (loopCount > max) 
		{
	   	 log(ast.getLineNo(), "Max number of loops exceeded", max);
		}
		break;
	}
  }
}