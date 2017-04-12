package anluit.cg.results;

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
	
	public void addResults(SentenceResult sr) {
		results.add(sr);
	}
	
	public Iterator<SentenceResult> getIterator() {
		return results.iterator();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/*
	 * Returns a name without any special characters.
	 * This includes spaces, dashes, dots and commas.
	 * This method needs work.
	 */
	public String getNoSpecialCharactersName() {
		if (name == null) {
			return null;
		}
		
		String n = name;
		n = n.replace(" ", "");
		n = n.replace("-", "");
		n = n.replace(".", "");
		n = n.replace(",", "");
		return n;
	}
	
}
