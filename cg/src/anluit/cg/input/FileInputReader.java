package anluit.cg.input;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileInputReader {
	
	public static String read(String filepath) {
		return read(filepath, StandardCharsets.UTF_8);
	}
	
	public static String read(String filepath, Charset encoding) {
		try {
			return new String(Files.readAllBytes(Paths.get(filepath)), encoding);
		} catch (Exception e) {
			return "";
		}
	}
	
}
