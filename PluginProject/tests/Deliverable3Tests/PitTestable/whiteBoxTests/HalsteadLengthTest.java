package Deliverable3Tests.PitTestable.whiteBoxTests;

import TeamRebecca.HalsteadMetricsCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class HalsteadLengthTest {

	@Test
	public void testGetHalsteadLength01() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.
		DetailAST ast = new DetailAST();

		doReturn(1).when(test).getOperandsCount(); // operand
		doReturn(1).when(test).getOperatorsCount(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		assertEquals(2, test.getHalsteadLength());
	}

	@Test
	public void testGetHalsteadLength02() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.
		DetailAST ast = new DetailAST();

		doReturn(135).when(test).getOperandsCount(); // operand
		doReturn(65).when(test).getOperatorsCount(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		assertEquals(200, test.getHalsteadLength());
	}

	@Test
	public void testGetHalsteadLength03() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.
		DetailAST ast = new DetailAST();

		doReturn(1).when(test).getOperandsCount(); // operand
		doReturn(1).when(test).getOperatorsCount(); // operator
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		assertEquals(2, test.getHalsteadLength());
	}

}
