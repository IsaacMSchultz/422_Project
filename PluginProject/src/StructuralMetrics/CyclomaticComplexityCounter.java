package StructuralMetrics;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

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
public class CyclomaticComplexityCounter extends AbstractCheck {

	int cyclomaticComplexity = 0;

	/**
	 * A key is pointing to the warning message text in "messages.properties" file.
	 */
	public static final String MSG_KEY = "cyclomaticComplexity";

	/** The initial current value. */
	private static final BigInteger INITIAL_VALUE = BigInteger.ONE;

	/** Default allowed complexity. */
	private static final int DEFAULT_COMPLEXITY_VALUE = 10;

	/** Stack of values - all but the current value. */
	private final Deque<BigInteger> valueStack = new ArrayDeque<>();

	/** The current value. */
	private BigInteger currentValue = INITIAL_VALUE;

	/** Threshold to report violation for. */
	private int max = DEFAULT_COMPLEXITY_VALUE;

	@Override
	public void beginTree(DetailAST rootAST) {
		currentValue = INITIAL_VALUE; // Make sure to restart the cound each time the check is run.
		cyclomaticComplexity = 0;
	}

	public int getCycles() {
		return cyclomaticComplexity;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT,
				TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF,
				TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH, TokenTypes.QUESTION,
				TokenTypes.LAND, TokenTypes.LOR, };
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT,
				TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF,
				TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH, TokenTypes.QUESTION,
				TokenTypes.LAND, TokenTypes.LOR, };
	}

	@Override
	public final int[] getRequiredTokens() {
		return new int[] { TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
				TokenTypes.STATIC_INIT, };
	}

	@Override
	public void visitToken(DetailAST ast) {
		switch (ast.getType()) {
		case TokenTypes.CTOR_DEF:
		case TokenTypes.METHOD_DEF:
		case TokenTypes.INSTANCE_INIT:
		case TokenTypes.STATIC_INIT:
			visitMethodDef();
			break;
		default:
			visitTokenHook(ast);
		}
		System.out.println("TEST");
	}

	@Override
	public void leaveToken(DetailAST ast) {
		switch (ast.getType()) {
		case TokenTypes.CTOR_DEF:
		case TokenTypes.METHOD_DEF:
		case TokenTypes.INSTANCE_INIT:
		case TokenTypes.STATIC_INIT:
			leaveMethodDef(ast);
			break;
		default:
			break;
		}
	}

	/**
	 * Hook called when visiting a token. Will not be called the method definition
	 * tokens.
	 *
	 * @param ast the token being visited
	 */
	private void visitTokenHook(DetailAST ast) {
		if (ast.getType() != TokenTypes.LITERAL_SWITCH) {
			incrementCurrentValue(BigInteger.ONE);
		}
	}

	/**
	 * Process the end of a method definition.
	 *
	 * @param ast the token representing the method definition
	 */
	private void leaveMethodDef(DetailAST ast) {
		final BigInteger bigIntegerMax = BigInteger.valueOf(max);
		if (currentValue.compareTo(bigIntegerMax) > 0) {
			System.out.println("Cyclomatic complexity: " + currentValue.toString());
			cyclomaticComplexity = currentValue.intValue();
			// This is when the original would report the cyclomatic complexity.
		}
		popValue();
	}

	/**
	 * Increments the current value by a specified amount.
	 *
	 * @param amount the amount to increment by
	 */
	private void incrementCurrentValue(BigInteger amount) {
		currentValue = currentValue.add(amount);
	}

	/** Push the current value on the stack. */
	private void pushValue() {
		valueStack.push(currentValue);
		currentValue = INITIAL_VALUE;
	}

	/**
	 * Pops a value off the stack and makes it the current value.
	 */
	private void popValue() {
		currentValue = valueStack.pop();
	}

	/** Process the start of the method definition. */
	private void visitMethodDef() {
		pushValue();
	}

}
