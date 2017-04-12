package anluit.cg.results;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author simon
 * 
 * Contains the result of every description.
 */
public class SuiteResult {

	private ArrayList<DescriptionResult> results = new ArrayList<DescriptionResult>();
	
	public void addResults(DescriptionResult dr) {
		results.add(dr);
	}
	
	public Iterator<DescriptionResult> getIterator() {
		return results.iterator();
	}
	
	// for debug purposes
	public void print() {
		Iterator<DescriptionResult> it1 = this.getIterator();
		while (it1.hasNext()) {
			DescriptionResult dr = it1.next();
			Iterator<SentenceResult> it2 = dr.getIterator();
			while (it2.hasNext()) {
				SentenceResult ser = it2.next();
				Iterator<Result> it3 = ser.getIterator();
				while (it3.hasNext()) {
					Result res = it3.next();
				}
			}
		}
	}
	
}