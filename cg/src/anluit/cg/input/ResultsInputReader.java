package anluit.cg.input;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import anluit.cg.results.DescriptionResult;
import anluit.cg.results.Result;
import anluit.cg.results.SentenceResult;
import anluit.cg.results.SuiteResult;

public class ResultsInputReader {
	
	public SuiteResult read(String filepath) {
		if (filepath == null) {
			return new SuiteResult();
		}
		
		String content = FileInputReader.read(filepath);
		
		if (content.equals("")) {
			return new SuiteResult();
		}
		
		JSONArray jsonDescriptions = (JSONArray) JSONValue.parse(content);
		SuiteResult sr = new SuiteResult();
		
		// Read sentences for each description
		for (int i = 0; i < jsonDescriptions.size(); i++) {
			JSONObject jsonDescription = (JSONObject) jsonDescriptions.get(i);
			String descriptionName = (String) jsonDescription.get("name");
			JSONArray jsonSentences = (JSONArray) jsonDescription.get("sentences");
			DescriptionResult dr = new DescriptionResult(descriptionName);
			
			for (int j = 0; j < jsonSentences.size(); j++) {
				JSONObject jsonSentence = (JSONObject) jsonSentences.get(j);
				SentenceResult ser = new SentenceResult((String) jsonSentence.get("sentence"));
				
				JSONArray jsonResults = (JSONArray) jsonSentence.get("results");
				
				for (int k = 0; k < jsonResults.size(); k++) {
					JSONObject jsonResult = (JSONObject) jsonResults.get(k);
					String type = (String) jsonResult.get("type");
					String value = (String) jsonResult.get("value");
					Result res = new Result(type, value);
					ser.addResult(res);
				}
				
				dr.addResults(ser);
			}
			
			sr.addResults(dr);
		}
		
		return sr;
	}
	
}
