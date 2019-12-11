package Deliverable3Tests.whiteBoxTests;

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
public class HalsteadEffortTest {
	@Test
	public void testGetHalsteadEffort01() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.
		DetailAST ast = new DetailAST();

		doReturn(1.0).when(test).getHalsteadDifficulty();
		doReturn(6.0).when(test).getHalsteadVolume();
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);
		
		// effort = volume * difficulty
		// effort = 1*6 = 6
		assertEquals(6, test.getHalsteadEffort(), 0.1);
	}
	
	@Test
	public void testGetHalsteadEffort02() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.
		DetailAST ast = new DetailAST();

		doReturn(135.0).when(test).getHalsteadDifficulty();
		doReturn(65.0).when(test).getHalsteadVolume();
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// effort = volume * difficulty
		// effort = 135*65 =
		assertEquals(8775 , test.getHalsteadEffort(), 0.1);
	}
}
