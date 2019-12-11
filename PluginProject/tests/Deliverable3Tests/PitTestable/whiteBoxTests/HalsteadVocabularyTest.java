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
public class HalsteadVocabularyTest {
	
	@Test
	public void testGetHalsteadVocabulary01() { //test with known values
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.
		DetailAST ast = new DetailAST();

		doReturn(99).when(test).getUniqueOperands();
		doReturn(39).when(test).getUniqueOperators();
		test.beginTree(ast); // begin the tree
		test.finishTree(ast);

		// halsteadVocabulary = uniqueOperands + uniqueOperators;
		// 99 + 39 = 138
		assertEquals(138, test.getHalsteadVocabulary());
	}

	@Test
	public void testGetHalsteadVocabulary02() { //test with known values
		HalsteadMetricsCheck test = spy(new HalsteadMetricsCheck());
		doReturn(1).when(test).getLOC(); // lines of code gets run every time.
		DetailAST ast = new DetailAST();

		doReturn(9649).when(test).getUniqueOperands();
		doReturn(32).when(test).getUniqueOperators();
		test.beginTree(ast); // begin the tree
		test.finishTree(ast);

		// halsteadVocabulary = uniqueOperands + uniqueOperators;
		// 9649 + 32 = 138
		assertEquals(9681, test.getHalsteadVocabulary());
	}
}
