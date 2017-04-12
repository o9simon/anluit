package anluit.cg.test.input;

import anluit.cg.input.ResultsInputReader;
import anluit.cg.results.SuiteResult;
import junit.framework.*;

public class ResultsInputReaderTest extends TestCase {

	public void testReadNull() {
		ResultsInputReader rir = new ResultsInputReader();
		assertTrue(rir.read(null) instanceof SuiteResult);
	}
	
	public void testReadInvalidPath() {
		ResultsInputReader rir = new ResultsInputReader();
		assertTrue(rir.read("invalid_path") instanceof SuiteResult);
	}
	
}
