// NEEDS TO BE UPDATED

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

;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DetailAST.class})
public class MaintainabilityIndexTest {

	@Test
	public void testGetMaintainabilityIndex01() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		DetailAST ast = new DetailAST();

		doReturn(1235.3213).when(test).getHalsteadVolume();
		doReturn(45).when(test).getCyclomaticComplexity();
		doReturn(50234).when(test).getLOC();
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// MaintainabilityIndex = 171 - 5.2 * log2(Volume) - 0.23 * cyclo - 16.2 *
		// log2(LOC) + 50;
		// 171 - 5.2 * log2(1235.3213) - 0.23 * 45 - 16.2 * log2(50234) + 50
		assertEquals(-95.74278, test.getMaintainabilityIndex(), 0.1);
	}

	@Test
	public void testGetMaintainabilityIndex02() {
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		DetailAST ast = new DetailAST();

		doReturn(9541.564).when(test).getHalsteadVolume();
		doReturn(135).when(test).getCyclomaticComplexity();
		doReturn(165).when(test).getLOC();
		test.beginTree(ast); // begin the tree

		test.finishTree(ast);

		// MaintainabilityIndex = 171 - 5.2 * log2(Volume) - 0.23 * cyclo - 16.2 *
		// log2(LOC) + 50;
		// 171 - 5.2 * log2(9541.564) - 0.23 * 135 - 16.2 * log2(165) + 50
		assertEquals(1.87152, test.getMaintainabilityIndex(), 0.1);
	}
}
