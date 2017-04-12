package anluit.cg.generator.action;

import anluit.cg.generator.SelectorGenerator;
import anluit.cg.results.SentenceResult;

public class AssertActionGenerator extends ActionGenerator {

	private SentenceResult sent;
	
	public AssertActionGenerator(SentenceResult sent) {
		this.sent = sent;
	}
	
	@Override
	public String[] generate() {
		if (sent == null) {
			return null;
		}
		
		String selector = (new SelectorGenerator(sent)).generate();
		
		if (selector != null) {
			return new String[]{"assertTrue(" + selector + ".exists());"};
		}
		
		String param1 = sent.getTypeValue("assert-param-1");
		String param2 = sent.getTypeValue("assert-param-2");
		String type = sent.getTypeValue("assert-type");
		
		if (param1 != null && param2 != null && type != null) {
			if (type.equals("equal")){
				return new String[]{"assertEquals(\"" + param1 + "\", \"" + param2 + "\");"};
			}
			else if (type.equals("greater than")){
				return new String[]{"assertTrue(" + param1 + " > " + param2 + ");"};
			}
			else if (type.equals("lesser than")){
				return new String[]{"assertTrue(" + param1 + " < " + param2 + ");"};
			}
		}
		
		return null;
	}

}
