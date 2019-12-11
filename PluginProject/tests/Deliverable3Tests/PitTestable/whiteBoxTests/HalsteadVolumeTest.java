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
public class HalsteadVolumeTest {

	@Test
	public void testGetHalsteadVolume01() { //test with known values
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.
		DetailAST ast = new DetailAST();

		doReturn(37).when(test).getHalsteadLength();
		doReturn(16).when(test).getHalsteadVocabulary();
		test.beginTree(ast); // begin the tree
		test.finishTree(ast);

		// length * log2(vocabulary)
		// 37 * log2(16) = 148
		assertEquals(148, test.getHalsteadVolume(), 0.1);
	}

	@Test
	public void testGetHalsteadVolume02() { //test with known values
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.
		DetailAST ast = new DetailAST();

		doReturn(99).when(test).getHalsteadLength();
		doReturn(39).when(test).getHalsteadVocabulary();
		test.beginTree(ast); // begin the tree
		test.finishTree(ast);

		// length * log2(vocabulary)
		// 99 * log2(39) = 
		assertEquals(523.25481, test.getHalsteadVolume(), 0.1);
	}
}
