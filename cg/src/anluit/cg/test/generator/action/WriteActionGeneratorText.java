package anluit.cg.test.generator.action;

import anluit.cg.generator.action.WriteActionGenerator;
import anluit.cg.results.Result;
import anluit.cg.results.SentenceResult;
import junit.framework.*;

public class WriteActionGeneratorText extends TestCase {

	public void testGenerateNull() {
		WriteActionGenerator gen = new WriteActionGenerator(null);
		assertNull(gen.generate());
	}
	
	public void testGenerateSuccess() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		
		sent.addResult(new Result("selector-text", "test"));
		sent.addResult(new Result("action-text", "test"));

		WriteActionGenerator gen = new WriteActionGenerator(sent);
		assertEquals(gen.generate()[0], "(getDevice().findObject(new UiSelector().textMatches(\"(?i)(test)\"))).setText(\"test\");");
	}
	
	public void testGenerateEmptyString() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		
		sent.addResult(new Result("selector-text", "test"));

		WriteActionGenerator gen = new WriteActionGenerator(sent);
		assertEquals(gen.generate()[0], "(getDevice().findObject(new UiSelector().textMatches(\"(?i)(test)\"))).setText(\"\");");
	}
	
	public void testGenerateNoSelector() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		
		sent.addResult(new Result("action-text", "test"));

		WriteActionGenerator gen = new WriteActionGenerator(sent);
		assertNull(gen.generate());
	}
	
}
