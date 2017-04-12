package anluit.cg.generator.action;

import java.util.Iterator;

import anluit.cg.results.Result;
import anluit.cg.results.SentenceResult;

public class ActionGeneratorFactory {

	public static ActionGenerator create(SentenceResult sent) {
		if (sent == null) {
			return null;
		}
		
		Iterator<Result> iter = sent.getIterator();
		String action = "";
		
		// extract the action from the sentence result
		while (iter.hasNext()) {
			Result res = iter.next();
			if (res.getType().equals("action-type")) {
				action = res.getValue();
			}
		}
		
		if (action.equals("click")) {
			return new ClickActionGenerator(sent);
		} else if (action.equals("swipe")) {
			return new SwipeActionGenerator(sent);
		} else if (action.equals("wake up")) {
			return new WakeUpActionGenerator();
		} else if (action.equals("write")) {
			return new WriteActionGenerator(sent);
		} else if (action.equals("assert")) {
			return new AssertActionGenerator(sent);
		}
		
		return null;
	}
	
}
