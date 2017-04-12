package anluit.nlp.output;

import java.io.PrintWriter;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import anluit.nlp.results.DescriptionResult;
import anluit.nlp.results.Result;
import anluit.nlp.results.SentenceResult;
import anluit.nlp.results.SuiteResult;

public class ResultsOutputWriter {

	private String createJSON(SuiteResult result) {
		Iterator<DescriptionResult> itdr = result.getIterator();
		JSONArray jsonDescriptions = new JSONArray();
		while (itdr.hasNext()) {
			DescriptionResult dr = itdr.next();
			Iterator<SentenceResult> itsr = dr.getIterator();
			JSONObject jsonDescription = new JSONObject();
			JSONArray jsonSentences = new JSONArray();
			
			jsonDescription.put("name", dr.getName());
			
			while (itsr.hasNext()) {
				SentenceResult sr = itsr.next();
				Iterator<Result> itr = sr.getIterator();
				JSONArray jsonResults = new JSONArray();
				JSONObject jsonSentence = new JSONObject();

				while (itr.hasNext()) {
					Result res = itr.next();
					JSONObject jsonRes = new JSONObject();
					jsonRes.put("type", res.getType());
					jsonRes.put("value", res.getValue());
					jsonResults.add(jsonRes);
				}
				
				jsonSentence.put("sentence", sr.getSentence());
				jsonSentence.put("results", jsonResults);
				
				jsonSentences.add(jsonSentence);
			}
			
			jsonDescription.put("sentences", jsonSentences);
			
			jsonDescriptions.add(jsonDescription);
		}
		
		return jsonDescriptions.toJSONString();
	}
	
	public void write(String filename, SuiteResult sr) {
		OutputWriter.write(filename, createJSON(sr));
	}
	
}
