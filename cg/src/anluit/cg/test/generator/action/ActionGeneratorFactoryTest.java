package anluit.cg.test.generator.action;

import anluit.cg.generator.action.ActionGeneratorFactory;
import anluit.cg.generator.action.AssertActionGenerator;
import anluit.cg.generator.action.ClickActionGenerator;
import anluit.cg.generator.action.SwipeActionGenerator;
import anluit.cg.generator.action.WakeUpActionGenerator;
import anluit.cg.generator.action.WriteActionGenerator;
import anluit.cg.results.Result;
import anluit.cg.results.SentenceResult;
import junit.framework.*;

public class ActionGeneratorFactoryTest extends TestCase {

	public void testCreateNull() {
		assertEquals(ActionGeneratorFactory.create(null), null);
	}
	
	public void testCreateActionTypeEmptyString() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		sent.addResult(new Result("action-type", ""));
		assertEquals(ActionGeneratorFactory.create(sent), null);
	}
	
	public void testCreateActionTypeNotExist() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		sent.addResult(new Result("action-type", "test"));
		assertEquals(ActionGeneratorFactory.create(sent), null);
	}
	
	public void testCreateActionTypeClick() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		sent.addResult(new Result("action-type", "click"));
		assertTrue(ActionGeneratorFactory.create(sent) instanceof ClickActionGenerator);
	}
	
	public void testCreateActionTypeSwipe() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		sent.addResult(new Result("action-type", "swipe"));
		assertTrue(ActionGeneratorFactory.create(sent) instanceof SwipeActionGenerator);
	}
	
	public void testCreateActionTypeWakeUp() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		sent.addResult(new Result("action-type", "wake up"));
		assertTrue((ActionGeneratorFactory.create(sent)) instanceof WakeUpActionGenerator);
	}

	public void testCreateActionTypeWrite() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		sent.addResult(new Result("action-type", "write"));
		assertTrue(ActionGeneratorFactory.create(sent) instanceof WriteActionGenerator);
	}
	
	public void testCreateActionTypeAssert() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		sent.addResult(new Result("action-type", "assert"));
		assertTrue(ActionGeneratorFactory.create(sent) instanceof AssertActionGenerator);
	}
	
}
