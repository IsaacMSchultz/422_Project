package StructuralMetrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.lang.Math;

public class MaintainabilityIndex extends AbstractCheck {

	private CyclomaticComplexityCounter cyclomaticComplexity = new CyclomaticComplexityCounter();
//    private HalsteadVolume halsteadVolume = new HalsteadVolume();
//    private LinesOfCode linesOfCode = new LinesOfCode();

	@Override
	public void beginTree(DetailAST rootAST) {
		cyclomaticComplexity.beginTree(rootAST);
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		int G = cyclomaticComplexity.getCycles();
		int V = 0; // halsteadVolume.getVolume();
		int LOC = 0; // linesOfCode.getLOC();
		System.out.println(G);
		double MI = 171 - 5.2 * log2(V) - 0.23 * G - 16.2 * log2(LOC) + 50;
		log(0, "Maintainability index: " + Double.toString(MI));
	}

	@Override
	public int[] getDefaultTokens() {
		System.out.println("TESTSTART");

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
		cyclomaticComplexity.visitToken(ast);
	}

	@Override
	public void leaveToken(DetailAST ast) {
		cyclomaticComplexity.leaveToken(ast);
	}

	public static double log2(int x) {
		return (Math.log(x) / Math.log(2));
	}
}
