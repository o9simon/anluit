package anluit.cg.test.results;

import anluit.cg.results.Result;
import anluit.cg.results.SentenceResult;
import junit.framework.*;

public class SentenceResultTest extends TestCase {

	public void testGetTypeValueNull() {
		SentenceResult sr = new SentenceResult("This is a sentence.");
		assertNull(sr.getTypeValue(null));
	}
	
	public void testGetTypeValueEmptyString() {
		SentenceResult sr = new SentenceResult("This is a sentence.");
		assertNull(sr.getTypeValue(""));
	}
	
	public void testGetTypeValueNotExist() {
		SentenceResult sr = new SentenceResult("This is a sentence.");
		assertNull(sr.getTypeValue("non-existent-type"));
	}
	
	public void testGetTypeValueSuccess() {
		SentenceResult sr = new SentenceResult("This is a sentence.");
		Result r = new Result("action", "click");
		sr.addResult(r);
		assertEquals(sr.getTypeValue("action"), "click");
	}
	
}
