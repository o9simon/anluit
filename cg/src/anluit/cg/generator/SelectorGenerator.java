package anluit.cg.generator;

import java.util.HashMap;

import anluit.cg.results.SentenceResult;

public class SelectorGenerator {
	
	private SentenceResult sent;
	
	public SelectorGenerator(SentenceResult sent) {
		this.sent = sent;
	}

	public String generate() {
		if (sent == null) {
			return null;
		}
		
		StringBuilder gen = new StringBuilder("(getDevice().findObject(new UiSelector()");
		String text = sent.getTypeValue("selector-text");
		String desc = sent.getTypeValue("selector-description");
		String clazz = getRealClassName(sent.getTypeValue("selector-class"));
		String status = sent.getTypeValue("selector-status");
		String textType = sent.getTypeValue("selector-text-type");
		boolean notNull = false;

		if (text != null) {
			if (textType != null && textType.equals("contain")) {
				gen.append(".textContains(\"").append(escapeRegexCharacters(text)).append("\")");
			} else {
				gen.append(".textMatches(\"(?i)(").append(escapeRegexCharacters(text)).append(")\")");
			}
			notNull = true;
		}
		
		if (desc != null) {
			gen.append(".descriptionMatches(\"(?i)(").append(escapeRegexCharacters(desc)).append(")\")");
			notNull = true;
		}
		
		if (clazz != null) {
			gen.append(".className(\"").append(clazz).append("\")");
			notNull = true;
		}
		
		if (status != null) {
			if (status.equals("on")) {
				gen.append(".text(\"ON\")");
			} else if (status.equals("off")) {
				gen.append(".text(\"OFF\")");
			} else if (status.equals("empty")) {
				gen.append(".text(\"\")");
			}
		}
		
		gen.append("))");
		
		if (notNull) {
			return gen.toString();
		} else {
			return null;
		}
	}
	
	private String getRealClassName(String clazz) {
		if (clazz == null) {
			return null;
		}
		
		HashMap<String, String> table = new HashMap<String, String>();
		table.put("switch", "android.widget.Switch");
		table.put("textview", "android.widget.TextView");
		table.put("edittext", "android.widget.EditText");
		table.put("button", "android.widget.Button");
		table.put("image", "android.widget.ImageButton");
		table.put("checkbox", "android.widget.CheckBox");
		table.put("radio", "android.widget.RadioButton");
		table.put("datepicker", "android.widget.DatePicker");
		
		if (table.containsKey(clazz)) {
			return table.get(clazz);
		}
		
		return null;
	}
	
	private String escapeRegexCharacters(String text) {	
		text = text.replace("[", "\\\\[");
		text = text.replace("]", "\\\\]");
		text = text.replace("{", "\\\\{");
		text = text.replace("}", "\\\\}");
		text = text.replace("$", "\\\\$");
		text = text.replace(".", "\\\\.");
		text = text.replace("|", "\\\\|");
		text = text.replace("?", "\\\\?");
		text = text.replace("*", "\\\\*");
		text = text.replace("+", "\\\\+");
		text = text.replace("(", "\\\\(");
		text = text.replace(")", "\\\\)");
		text = text.replace("^", "\\\\^");
		
		return text;
	}
	
}
