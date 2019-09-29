package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CommentsCheck extends AbstractCheck {

  private int max = 30; 
  int countComments;
  
  public void beginTree(DetailAST rootAST) {
	  countComments = 0;
  }
  
  public void finishTree(DetailAST rootAST) {	 
  }

  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.COMMENT_CONTENT };
  }
  @Override
  public boolean isCommentNodesRequired() {
	  return true;
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.COMMENT_CONTENT };
  }

  public void setMax(int limit) {
    max = limit;
  }

  @Override
  public void visitToken(DetailAST ast) {
	  
	countComments++;
    if (countComments > max) {
    	 log(ast.getLineNo(), "commentlimit", max);
    }
  }
}
