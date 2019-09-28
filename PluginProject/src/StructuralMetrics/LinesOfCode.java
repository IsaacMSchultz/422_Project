package StructuralMetrics;

import java.io.File;

import com.puppycrawl.tools.checkstyle.StatelessCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractFileSetCheck;
import com.puppycrawl.tools.checkstyle.api.FileText;

@StatelessCheck
public class LinesOfCode extends AbstractFileSetCheck {

	private int LOC = 0;

	@Override
	protected void processFiltered(File file, FileText fileText) {
		LOC = fileText.size();
	}
	
	public int getLOC() {
		return LOC;
	}
}
