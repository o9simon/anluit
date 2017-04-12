package anluit.cg.test.generator.action;

import anluit.cg.generator.action.ClickActionGenerator;
import anluit.cg.results.Result;
import anluit.cg.results.SentenceResult;
import junit.framework.*;

public class ClickActionGeneratorTest extends TestCase {

	public void testGenerateNull() {
		ClickActionGenerator gen = new ClickActionGenerator(null);
		assertNull(gen.generate());
	}
	
	public void testGenerateSearch() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		
		sent.addResult(new Result("action-button", "search"));

		ClickActionGenerator gen = new ClickActionGenerator(sent);
		assertEquals(gen.generate()[0], "getDevice().pressSearch();");
	}
	
}
