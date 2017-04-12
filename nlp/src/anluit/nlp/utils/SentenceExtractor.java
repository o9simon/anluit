package anluit.nlp.utils;

import java.io.FileInputStream;
import java.io.IOException;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;

public class SentenceExtractor {

	private static SentenceExtractor instance;
	
	private SentenceModel sentenceModel;

	private SentenceExtractor(String modelPath) throws InvalidFormatException, IOException {
		sentenceModel = new SentenceModel(new FileInputStream(modelPath));
	}

	public static SentenceExtractor getInstance(String modelPath) throws InvalidFormatException, IOException {
		if (instance == null) {
			instance = new SentenceExtractor(modelPath);
		}
		return instance;
	}

	public String[] getSentences(String description) {
		return (new SentenceDetectorME(sentenceModel).sentDetect(description));
	}

}