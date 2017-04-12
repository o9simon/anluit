package anluit.nlp.input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import anluit.nlp.rules.Group;
import anluit.nlp.rules.Rule;
import anluit.nlp.rules.RuleSet;
import anluit.nlp.rules.SynonymMap;

public class RulesInputReader {
	
	private RuleSet rules = new RuleSet();
	private SynonymMap synonyms = new SynonymMap();
	
	public RulesInputReader(String filepath) {
		this.read(filepath);
	}
	
	public RuleSet getRules() {
		return rules;
	}

	public SynonymMap getSynonyms() {
		return synonyms;
	}

	public void read(String filepath) {
		String content = FileInputReader.read(filepath);
		JSONObject jsonTop = (JSONObject) JSONValue.parse(content);
		
		JSONArray jsonRules = (JSONArray) jsonTop.get("rules");
		JSONArray jsonSynonyms = (JSONArray) jsonTop.get("synonyms");

		// Read synonyms
		for (int i = 0; i < jsonSynonyms.size(); i++) {
			JSONObject jsonSynonym = (JSONObject) jsonSynonyms.get(i);
			
			@SuppressWarnings("unchecked") //Using legacy API
			Iterator<Entry<String, JSONArray>> iter = jsonSynonym.entrySet().iterator();
			
			while(iter.hasNext()){
				Entry<String, JSONArray> entry = iter.next();
				JSONArray jsonSynonymWords = (JSONArray) entry.getValue();
				ArrayList<String> syn = new ArrayList<String>();
				for (int j = 0; j < jsonSynonymWords.size(); j++) {
					syn.add(jsonSynonymWords.get(j).toString());
				}
				this.synonyms.add(entry.getKey().toString(), syn.toArray(new String[syn.size()]));
			}
		}
		
		// Read rules
		for (int i = 0; i < jsonRules.size(); i++) {
			JSONObject jsonRule = (JSONObject) jsonRules.get(i);
			String regex = (String) jsonRule.get("regex");
			
			JSONArray jsonGroups = (JSONArray) jsonRule.get("groups");
			ArrayList<Group> groups = new ArrayList<Group>();
			for (int j = 0; j < jsonGroups.size(); j++) {
				JSONObject jsonGroup = (JSONObject) jsonGroups.get(j);
				
				int index = Integer.parseInt((String) jsonGroup.get("index"));
				String type = (String) jsonGroup.get("type");
				int reliability = Integer.parseInt((String) jsonGroup.get("reliability"));
				
				groups.add(new Group(index, type, reliability));
			}
			
			rules.addRule(new Rule(Pattern.compile(regex), groups.toArray(new Group[groups.size()])));
		}
	}
	
}
