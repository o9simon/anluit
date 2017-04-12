package anluit.cg.results;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Contains all the results for one sentence.
 * 
 * @author simon
 */
public class SentenceResult {

	private String sentence;
	private ArrayList<Result> results = new ArrayList<Result>();
	
	public SentenceResult(String sentence) {
		this.sentence = sentence;
	}
	
	public void addResult(Result r) {
		results.add(r);
	}
	
	public Iterator<Result> getIterator() {
		return results.iterator();
	}
	
	public String getTypeValue(String type) {
		Iterator<Result> iter = getIterator();
		
		while (iter.hasNext()) {
			Result res = iter.next();
			if (res.getType().equals(type)) {
				return res.getValue();
			}
		}
		
		return null;	
	}
	
	public String getSentence() {
		return sentence;
	}
	
}
