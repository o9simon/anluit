package anluit.nlp.input;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SuiteInputReader {

	public static Map<String , String> read(String folderPath) {
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		Map<String , String> contents = new HashMap<String, String>();
		//String[] contents = new String[listOfFiles.length];

		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			if (file.isFile() && file.getName().endsWith(".txt")) {
				contents.put(removeExtension(file.getName()), FileInputReader.read(file.getAbsolutePath()));
			}
		}
		
		return contents;
	}
	
	public static String[] mockup() {
		return new String[] {"Click on 'Settings'. Write on the button with the text 'Wi-Fi' the text Hello. Click on the first switch."};
	}
	
	private static String removeExtension(String fname) {
		int pos = fname.lastIndexOf(".");
		if (pos > 0) {
		    fname = fname.substring(0, pos);
		}
		return fname;
	}
	
}
