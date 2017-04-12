package anluit.cg.test.generator.action;

import anluit.cg.generator.action.SwipeActionGenerator;
import anluit.cg.results.Result;
import anluit.cg.results.SentenceResult;
import junit.framework.*;

public class SwipeActionGeneratorTest extends TestCase {

	public void testGenerateNull() {
		SwipeActionGenerator gen = new SwipeActionGenerator(null);
		assertNull(gen.generate());
	}
	
	public void testGenerateLeft() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		
		sent.addResult(new Result("action-direction", "left"));

		SwipeActionGenerator gen = new SwipeActionGenerator(sent);
		assertEquals(gen.generate()[0], "getDevice().swipe((int)(getDevice().getDisplayWidth() * 0.1), (int)(getDevice().getDisplayHeight() * 0.5), (int)(getDevice().getDisplayWidth() * 0.9), (int)(getDevice().getDisplayHeight() * 0.5), 10); // swipe left");
	}
	
}
