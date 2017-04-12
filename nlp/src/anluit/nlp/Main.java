package anluit.nlp;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;

import anluit.nlp.input.RulesInputReader;
import anluit.nlp.input.SuiteInputReader;
import anluit.nlp.output.ResultsOutputWriter;
import anluit.nlp.results.DescriptionResult;
import anluit.nlp.results.Result;
import anluit.nlp.results.SentenceResult;
import anluit.nlp.results.SuiteResult;
import anluit.nlp.rules.Group;
import anluit.nlp.rules.Rule;
import anluit.nlp.rules.RuleSet;
import anluit.nlp.rules.SynonymMap;
import anluit.nlp.utils.SentenceExtractor;
import opennlp.tools.util.InvalidFormatException;

public class Main {
	
	public static void run(String descriptionFolderpath, String rulesFilepath, String sentenceModelFilepath, String resultsFilepath) throws InvalidFormatException, IOException {
		// Descriptions to be analyzed
		Map<String, String> descriptions = SuiteInputReader.read(descriptionFolderpath);
		
		// Read the rules from rules.json
		RulesInputReader reader = new RulesInputReader(rulesFilepath);
		RuleSet rules = reader.getRules();
		
		// Create new rules from the synonyms
		SynonymMap synonyms = reader.getSynonyms();
		rules.generateRules(synonyms);

		Rule[] rulesArr = rules.getRules();
		
		// Storage for the result of the suite of descriptions
		SuiteResult sr = new SuiteResult();
		
		// Compute each description
		for (String descriptionKey: descriptions.keySet()) {
			System.out.println("Description: " + descriptionKey);
			DescriptionResult dr = new DescriptionResult(descriptionKey);
			
			// Separate description into sentences
			String sentences[] = SentenceExtractor.getInstance(sentenceModelFilepath).getSentences(descriptions.get(descriptionKey));
			
			// Compute results for each sentence
			for (String sentence: sentences) {
				System.out.print("\tSentence: " + sentence);
				
				SentenceResult r = new SentenceResult(sentence);
				sentence = sentence.toLowerCase(); // Lowercase sentence

				for (Rule rule: rulesArr) {
					Matcher m = rule.getPattern().matcher(sentence);

					if (m.matches()) {
						System.out.print(".");
						
						for (Group g: rule.getGroups()) {
							r.tryResult(new Result(g.getType(), synonyms.getKeySynonym(m.group(g.getIndex())), g.getReliability()));
						}
					}
				}
				
				System.out.println("");
				dr.add(r);
			}
			
			sr.add(dr);
		}
		
		// Write to disk the results
		ResultsOutputWriter row = new ResultsOutputWriter();
		row.write(resultsFilepath, sr);
				
		System.out.println("Operation completed.");
	}
	
	public static void main(String[] args) throws IOException {
		
		if (args.length < 4) {
			if (args.length == 1 && args[0].equals("-usage")) {
				System.out.println("nlp [descriptions folderpath] [rules filepath] [sentence model filepath] [output filepath]");
			} else {
				System.out.println("Not enough parameters. See usage.");
			}
			return;
		}
		
		String descriptionFolderpath = args[0];
		String rulesFilepath = args[1];
		String sentenceModelFilepath = args[2];
		String resultsFilepath = args[3];
		
		run(descriptionFolderpath, rulesFilepath, sentenceModelFilepath, resultsFilepath);
	}
}
