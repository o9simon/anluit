package anluit.cg.output;

import java.io.PrintWriter;

public class OutputWriter {
	
	public static void write(String filename, String content) {
		write(filename, content, "UTF-8");
	}
	
	public static void write(String filename, String content, String encoding) {
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(filename, encoding);
			writer.println(content);
		} catch (Exception e) {
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
		
	}
	
}
