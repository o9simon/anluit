package anluit.cg.generator.action;

import anluit.cg.results.SentenceResult;

public class SwipeActionGenerator extends ActionGenerator {
	
	private SentenceResult sent;
	
	public SwipeActionGenerator(SentenceResult sent) {
		this.sent = sent;
	}
	
	@Override
	public String[] generate() {
		if (sent == null) {
			return null;
		}
		
		String direction = sent.getTypeValue("action-direction");
		
		if (direction != null) {
			if (direction.equals("left")) {
				return new String[]{"getDevice().swipe((int)(getDevice().getDisplayWidth() * 0.1), (int)(getDevice().getDisplayHeight() * 0.5), (int)(getDevice().getDisplayWidth() * 0.9), (int)(getDevice().getDisplayHeight() * 0.5), 10); // swipe left"};
			} else if (direction.equals("right")) {
				return new String[]{"getDevice().swipe((int)(getDevice().getDisplayWidth() * 0.9), (int)(getDevice().getDisplayHeight() * 0.5), (int)(getDevice().getDisplayWidth() * 0.1), (int)(getDevice().getDisplayHeight() * 0.5), 10); // swipe right"};
			} else if (direction.equals("top")) {
				return new String[]{"getDevice().swipe((int)(getDevice().getDisplayWidth() * 0.5), (int)(getDevice().getDisplayHeight() * 0.1), (int)(getDevice().getDisplayWidth() * 0.5), (int)(getDevice().getDisplayHeight() * 0.9), 10); // swipe down"};
			} else if (direction.equals("down")) {
				return new String[]{"getDevice().swipe((int)(getDevice().getDisplayWidth() * 0.9), (int)(getDevice().getDisplayHeight() * 0.1), (int)(getDevice().getDisplayWidth() * 0.5), (int)(getDevice().getDisplayHeight() * 0.1), 10); // swipe down"};
			}
		}
		
		return null;
	}
	
}
