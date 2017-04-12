package anluit.nlp.results;

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
	
	public void tryResult(Result r) {
		if (r == null) {
			return;
		}
		
		int index = getIndex(r.getType());
		if (index == -1) {
			results.add(r);
		} else {
			if (r.getReliability() > results.get(index).getReliability()) {
				results.remove(index);
				results.add(r);
			}
		}
	}
	
	private int getIndex(String type) {
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getType().equals(type)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public Iterator<Result> getIterator() {
		return results.iterator();
	}
	
	public String getSentence() {
		return sentence;
	}
	
}
