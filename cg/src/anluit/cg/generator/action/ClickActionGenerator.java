package anluit.cg.generator.action;

import anluit.cg.generator.SelectorGenerator;
import anluit.cg.results.SentenceResult;

public class ClickActionGenerator extends ActionGenerator {

	private SentenceResult sent;
	
	public ClickActionGenerator(SentenceResult sent) {
		this.sent = sent;
	}
	
	@Override
	public String[] generate() {
		if (sent == null) {
			return null;
		}
		
		String phoneButton = sent.getTypeValue("action-button");
		if (phoneButton != null) {
			if (phoneButton.equals("search")) {
				return new String[]{"getDevice().pressSearch();"};
			} else if (phoneButton.equals("menu")) {
				return new String[]{"getDevice().pressMenu();"};
			} else if (phoneButton.equals("recent apps")) {
				return new String[]{"getDevice().pressRecentApps();"};
			} else if (phoneButton.equals("home")) {
				return new String[]{"getDevice().pressHome();"};
			} else if (phoneButton.equals("enter")) {
				return new String[]{"getDevice().pressEnter();"};
			} else if (phoneButton.equals("delete")) {
				return new String[]{"getDevice().pressDelete();"};
			} else if (phoneButton.equals("back")) {
				return new String[]{"getDevice().pressBack();"};
			} else if (phoneButton.equals("center")) {
				return new String[]{"getDevice().pressDPadCenter();"};
			} else if (phoneButton.equals("down")) {
				return new String[]{"getDevice().pressDPadDown();"};
			} else if (phoneButton.equals("left")) {
				return new String[]{"getDevice().pressDPadLeft();"};
			} else if (phoneButton.equals("right")) {
				return new String[]{"getDevice().pressDPadRight();"};
			} else if (phoneButton.equals("up")) {
				return new String[]{"getDevice().pressDPadUp();"};
			}
		}
		
		String selector = (new SelectorGenerator(sent)).generate();
		return new String[]{selector + ".click();"};
	}

}
