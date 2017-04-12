package anluit.nlp.results;

public class Result {

	private String type;
	private String value;
	private int reliability;
	
	public Result(String type, String value, int reliability) {
		this.type = type;
		this.value = value;
		this.reliability = reliability;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public int getReliability() {
		return reliability;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setReliability(int reliability) {
		this.reliability = reliability;
	}
	
}
