package anluit.nlp.rules;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;

public class SynonymMap {
	
	private Map<String, String[]> synonyms = new HashMap<String, String[]>();
	
	public void add(String key, String[] values) {
		synonyms.put(key, values);
	}
	
	public Iterator<Entry<String, String[]>> getIterator() {
		return (Iterator<Entry<String, String[]>>) synonyms.entrySet().iterator();
	}
	
	// for debugging
	public void print() {
		for (String key: synonyms.keySet()) {
			System.out.print(key + " = [");
			for (String v: synonyms.get(key)) {
				System.out.print(v + ",");
			}
			System.out.println("]");
		}
	}
	
	public String getKeySynonym(String value) {
		String[] values;
	    for (Entry<String, String[]> entry : synonyms.entrySet()) {
	    	values = entry.getValue();
	    	for (int i = 0; i < values.length; ++i) {
	    		if (value.equals(values[i])) {
		            return entry.getKey();
		        }
	    	}
	    }
	    return value;
	}
	
}
