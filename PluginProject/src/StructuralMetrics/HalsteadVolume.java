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

//Use these to keep track of the tokens required for children.
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Checks cyclomatic complexity against a specified limit. The complexity is
 * measured by the number of "if", "while", "do", "for", "?:", "catch",
 * "switch", "case", "&amp;&amp;" and "||" statements (plus one) in the body of
 * the member. It is a measure of the minimum number of possible paths through
 * the source and therefore the number of required tests. Generally 1-4 is
 * considered good, 5-7 ok, 8-10 consider re-factoring, and 11+ re-factor now!
 *
 * <p>
 * Check has following properties:
 *
 * <p>
 * <b>switchBlockAsSingleDecisionPoint</b> - controls whether to treat the whole
 * switch block as a single decision point. Default value is <b>false</b>
 *
 *
 */
@FileStatefulCheck
public class HalsteadVolume extends AbstractCheck {
	
	private int halsteadVolume;

	private OperandCountCheck operandCount = new OperandCountCheck();
	private OperatorCountCheck operatorCount = new OperatorCountCheck();

	// Store the tokens they accept in a list so that they can be easily searched.
	private ArrayList<Integer> operandTokens = arrayToList(operandCount.getDefaultTokens());
	private ArrayList<Integer> operatorTokens = arrayToList(operatorCount.getDefaultTokens());

	@Override
	public void beginTree(DetailAST rootAST) {
		// Call the begin tree function of each check we depend on.
		operandCount.beginTree(rootAST);
		operatorCount.beginTree(rootAST);
	}

	@Override
	public void visitToken(DetailAST ast) {
		if (operandTokens.contains(ast.getType()))
			operandCount.visitToken(ast);
		if (operatorTokens.contains(ast.getType()))
			operatorCount.visitToken(ast);
	}
	
	@Override
	public void finishTree(DetailAST rootAST) {
		// Call the begin tree function of each check we depend on.
		operandCount.finishTree(rootAST);
		operatorCount.finishTree(rootAST);
		
		int operands = operandCount.getCount();
		int operators = operatorCount.getCount();
		
//		halsteadVolume = 
	}

	@Override
	public int[] getDefaultTokens() {
		return ArrayConcatenator.concatArray(operandCount.getDefaultTokens(), operatorCount.getDefaultTokens());
	}

	@Override
	public int[] getAcceptableTokens() {
		return ArrayConcatenator.concatArray(operandCount.getAcceptableTokens(), operatorCount.getAcceptableTokens());
	}

	@Override
    public final int[] getRequiredTokens() {
    	return ArrayConcatenator.concatArray(operandCount.getRequiredTokens(), operatorCount.getRequiredTokens());
    }
	
	// Simple function to create an ArrayList from an integer array
	private ArrayList<Integer> arrayToList(int[] array) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for (int i : array) {
			returnList.add(i);
		}
		return returnList;
	}
}