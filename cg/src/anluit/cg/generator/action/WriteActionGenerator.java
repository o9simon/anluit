package anluit.cg.generator.action;

import anluit.cg.generator.SelectorGenerator;
import anluit.cg.results.SentenceResult;

public class WriteActionGenerator extends ActionGenerator {
	
	private SentenceResult sent;
	
	public WriteActionGenerator(SentenceResult sent) {
		this.sent = sent;
	}
	
	@Override
	public String[] generate() {
		if (sent == null) {
			return null;
		}
		
		String selector = (new SelectorGenerator(sent)).generate();
		
		if (selector == null) {
			return null;
		}
		
		String text = sent.getTypeValue("action-text");
		if (text == null) {
			text = "";
		}
		return new String[]{selector + ".setText(\"" + text + "\");"};
	}

}
