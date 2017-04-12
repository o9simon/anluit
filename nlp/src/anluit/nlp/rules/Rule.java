package anluit.nlp.rules;

import java.util.regex.Pattern;

public class Rule {
	
	private Pattern pattern;
	private Group[] groups;
	
	public Rule(Pattern pattern, Group[] groups) {
		this.pattern = pattern;
		this.groups = groups;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public Group[] getGroups() {
		return groups;
	}
	
	public String toString() {
		return pattern.toString();
	}
	
}
