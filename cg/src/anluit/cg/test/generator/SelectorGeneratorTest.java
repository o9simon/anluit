package anluit.cg.test.generator;

import anluit.cg.generator.SelectorGenerator;
import anluit.cg.results.Result;
import anluit.cg.results.SentenceResult;
import junit.framework.*;

public class SelectorGeneratorTest extends TestCase {

	public void testGenerateNull() {
		SelectorGenerator gen = new SelectorGenerator(null);
		assertNull(gen.generate());
	}
	
	public void testGenerateEmpty() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		SelectorGenerator gen = new SelectorGenerator(sent);
		assertNull(gen.generate());
	}

	public void testGenerateSuccess() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		
		sent.addResult(new Result("selector-text", "test"));

		SelectorGenerator gen = new SelectorGenerator(sent);
		assertEquals(gen.generate(), "(getDevice().findObject(new UiSelector().textMatches(\"(?i)(test)\")))");
	}
	
}
