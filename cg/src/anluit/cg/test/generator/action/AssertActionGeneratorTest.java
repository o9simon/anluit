package anluit.cg.test.generator.action;

import anluit.cg.generator.action.AssertActionGenerator;
import anluit.cg.results.SentenceResult;
import anluit.cg.results.Result;
import junit.framework.*;

public class AssertActionGeneratorTest extends TestCase {

	public void testGenerateNull() {
		AssertActionGenerator gen = new AssertActionGenerator(null);
		assertNull(gen.generate());
	}
	
	public void testGenerateEqual() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		
		sent.addResult(new Result("assert-param-1", "1"));
		sent.addResult(new Result("assert-param-2", "2"));
		sent.addResult(new Result("assert-type", "equal"));
		
		AssertActionGenerator gen = new AssertActionGenerator(sent);
		assertEquals(gen.generate()[0], "assertEquals(\"1\", \"2\");");
	}
	
	public void testGenerateGreaterThan() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		
		sent.addResult(new Result("assert-param-1", "1"));
		sent.addResult(new Result("assert-param-2", "2"));
		sent.addResult(new Result("assert-type", "greater than"));
		
		AssertActionGenerator gen = new AssertActionGenerator(sent);
		
		assertEquals(gen.generate()[0], "assertTrue(1 > 2);");
	}
	
	public void testGenerateLesserThan() {
		SentenceResult sent = new SentenceResult("This is a sentence.");
		
		sent.addResult(new Result("assert-param-1", "1"));
		sent.addResult(new Result("assert-param-2", "2"));
		sent.addResult(new Result("assert-type", "lesser than"));
		
		AssertActionGenerator gen = new AssertActionGenerator(sent);
		
		assertEquals(gen.generate()[0], "assertTrue(1 < 2);");
	}
	
}
