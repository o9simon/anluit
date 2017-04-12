package anluit.nlp.rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;


public class RuleSet {

	public Set<Rule> rules = new HashSet<Rule>();
	
	public void addRule(Rule r) {
		rules.add(r);
	}
	
	public void generateRules(SynonymMap synonyms) {
		this.generateRules(synonyms, 0);
	}
	
	private void generateRules(SynonymMap synonyms, int i) {
		ArrayList<Rule> newRules = new ArrayList<Rule>();
		
		for (Rule r: rules) {
			Iterator<Entry<String, String[]>> entries = synonyms.getIterator();
			while (entries.hasNext()) {
				Entry<String, String[]> entry = entries.next();
				String key = entry.getKey();
				String[] values = entry.getValue();
				
				if (r.getPattern().toString().contains(key)) {
					for (String value: values) {
						String newRegex = r.getPattern().toString().replace(key, value);
						newRules.add(new Rule(Pattern.compile(newRegex), r.getGroups()));
					}
				}
			}
		}
		
		rules.addAll(newRules);
		
		// Call this function 3 times to explore different levels of depth of synonyms.
		if (i < 3) {
			this.generateRules(synonyms, ++i);
		}
	}
	
	public Rule[] getRules() {
		return rules.toArray(new Rule[rules.size()]);
	}
	
	// for debugging
	public void print() {
		for (Rule r: rules) {
			System.out.println(r);
		}
	}
	
}
