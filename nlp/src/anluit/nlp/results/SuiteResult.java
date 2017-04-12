package anluit.nlp.results;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author simon
 * 
 * Contains the result of every description.
 */
public class SuiteResult {

	private ArrayList<DescriptionResult> results = new ArrayList<DescriptionResult>();
	
	public void add(DescriptionResult dr) {
		results.add(dr);
	}
	
	public Iterator<DescriptionResult> getIterator() {
		return results.iterator();
	}
	
}
