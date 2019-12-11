package Deliverable3Tests.PitTestable.whiteBoxTests;

import TeamRebecca.HalsteadMetricsCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class HalsteadDifficultyTest {

	@Test
	public void testGetHalsteadDifficulty1() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.

		DetailAST ast = new DetailAST();

		doReturn(1).when(test).getOperandsCount(); // operand
		doReturn(1).when(test).getUniqueOperators(); // operator
		doReturn(1).when(test).getUniqueOperands(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (1 / 2) * (1 / 1) = 1/2
		assertEquals(0.5, test.getHalsteadDifficulty(), 0.1);
	}
	
	@Test
	public void testGetHalsteadDifficulty2() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.

		DetailAST ast = new DetailAST();

		doReturn(20).when(test).getOperandsCount(); // operand
		doReturn(1).when(test).getUniqueOperators(); // operator
		doReturn(1).when(test).getUniqueOperands(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (1 / 2) * (20 / 1) = 10
		assertEquals(10, test.getHalsteadDifficulty(), 0.1);
	}
	
	@Test
	public void testGetHalsteadDifficulty3() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.

		DetailAST ast = new DetailAST();

		doReturn(20).when(test).getOperandsCount(); // operand
		doReturn(20).when(test).getUniqueOperators(); // operator
		doReturn(1).when(test).getUniqueOperands(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (20 / 2) * (20 / 1) = 200
		assertEquals(200, test.getHalsteadDifficulty(), 0.1);
	}
	
	@Test
	public void testGetHalsteadDifficulty4() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.

		DetailAST ast = new DetailAST();

		doReturn(37).when(test).getOperandsCount(); // operand
		doReturn(15).when(test).getUniqueOperators(); // operator
		doReturn(11).when(test).getUniqueOperands(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// halsteadDifficulty = (uniqueOperators / 2) * (operands / uniqueOperands)
		// halsteadDifficulty = (15 / 2) * (37 / 11) = 25.22727
		assertEquals(25.22727, test.getHalsteadDifficulty(), 0.1);
	}
}
