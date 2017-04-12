package anluit.nlp.rules;

public class Group {
	
	private int index;
	private String type;
	private int reliability;
	
	public Group(int index, String type, int reliability) {
		this.index = index;
		this.type = type;
		this.reliability = reliability;
	}

	public int getIndex() {
		return index;
	}

	public String getType() {
		return type;
	}

	public int getReliability() {
		return reliability;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setReliability(int reliability) {
		this.reliability = reliability;
	}
	
}
