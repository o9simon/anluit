package anluit.nlp.results;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author simon
 *
 * Contains the result for every sentence in a description.
 */
public class DescriptionResult {

	private String name;
	private ArrayList<SentenceResult> results = new ArrayList<SentenceResult>();
	
	public DescriptionResult(String name) {
		this.name = name;
	}
	
	public void add(SentenceResult sr) {
		results.add(sr);
	}
	
	public Iterator<SentenceResult> getIterator() {
		return results.iterator();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
