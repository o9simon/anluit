package anluit.nlp.test.results;

import java.util.Iterator;
import anluit.nlp.results.Result;
import anluit.nlp.results.SentenceResult;
import junit.framework.*;

public class SentenceResultTest extends TestCase {

	public void testTryResultNull(){
		SentenceResult sent = new SentenceResult("This is a sentence.");
		sent.tryResult(null);
		assertFalse(sent.getIterator().hasNext());
	}
	
	public void testTryResultSuccess(){
		SentenceResult sent = new SentenceResult("This is a sentence.");
		Result clickResult = new Result("action", "click", 5);
		Result moveResult = new Result("action", "move", 6);
		sent.tryResult(clickResult);
		sent.tryResult(moveResult);
		
		Iterator<Result> it = sent.getIterator();
		assertTrue(it.hasNext());
		assertTrue(it.next().getValue().equals("move"));
	}
	
	public void testTryResultFail(){
		SentenceResult sent = new SentenceResult("This is a sentence.");
		Result clickResult = new Result("action", "click", 5);
		Result moveResult = new Result("action", "move", 4);
		sent.tryResult(clickResult);
		sent.tryResult(moveResult);
		
		Iterator<Result> it = sent.getIterator();
		assertTrue(it.hasNext());
		assertTrue(it.next().getValue().equals("click"));
	}
	
	public void testTryResultFail2(){
		SentenceResult sent = new SentenceResult("This is a sentence.");
		Result clickResult = new Result("action", "click", 5);
		Result moveResult = new Result("action", "move", 5);
		sent.tryResult(clickResult);
		sent.tryResult(moveResult);
		
		Iterator<Result> it = sent.getIterator();
		assertTrue(it.hasNext());
		assertTrue(it.next().getValue().equals("click"));
	}

}
