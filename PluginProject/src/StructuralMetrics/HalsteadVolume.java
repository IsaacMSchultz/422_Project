package StructuralMetrics;
 
import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.metrics.*;
import java.util.regex.Pattern;
 
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.lang.Math;

/**
 * Checks cyclomatic complexity against a specified limit. The complexity is
 * measured by the number of "if", "while", "do", "for", "?:", "catch",
 * "switch", "case", "&amp;&amp;" and "||" statements (plus one) in the body of
 * the member. It is a measure of the minimum number of possible paths through
 * the source and therefore the number of required tests. Generally 1-4 is
 * considered good, 5-7 ok, 8-10 consider re-factoring, and 11+ re-factor now!
 *
 * <p>Check has following properties:
 *
 * <p><b>switchBlockAsSingleDecisionPoint</b> - controls whether to treat the whole switch
 * block as a single decision point. Default value is <b>false</b>
 *
 *
 */
@FileStatefulCheck
public class HalsteadVolume extends AbstractCheck {

    private OperandCountCheck operandCount = new OperandCountCheck();
    private OperatorCountCheck operatorCount = new OperatorCountCheck();
    
    @Override
    public void beginTree(DetailAST rootAST)
    {
    	operandCount.beginTree(rootAST);
    	operatorCount.beginTree(rootAST);
    }

    @Override
    public int[] getDefaultTokens() {
    	
    	int[] tokens = ArrayConcatenator.concatArray(operandCount.getDefaultTokens(), operatorCount.getDefaultTokens());
    	return tokens;
    }

    @Override
    public int[] getAcceptableTokens() {
    	int[] tokens = ArrayConcatenator.concatArray(operandCount.getAcceptableTokens(), operatorCount.getAcceptableTokens());
    	return tokens;
    }

    @Override
    public final int[] getRequiredTokens() {
    	int[] tokens = ArrayConcatenator.concatArray(operandCount.getRequiredTokens(), operatorCount.getRequiredTokens());
    	return tokens;
    }

    @Override
    public void visitToken(DetailAST ast) {
    	operandCount.visitToken(ast);
    	operatorCount.visitToken(ast);
    }
}
