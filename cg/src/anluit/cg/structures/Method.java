package anluit.cg.structures;

import java.util.ArrayList;
import java.util.Iterator;

public class Method {

	private String annotation;
	private String visibility;
	private String name;
	private ArrayList<Parameter> parameters = new ArrayList<Parameter>();
	private ArrayList<String> instructions = new ArrayList<String>();
	private ArrayList<String> throwz = new ArrayList<String>();
	private String returnType;
	
	public Method(String visibility, String returnType, String name) {
		this.visibility = visibility;
		this.returnType = returnType;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAnnotation() {
		return annotation;
	}
	
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	
	public String getVisibility() {
		return visibility;
	}
	
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	public Iterator<String> getInstructions() {
		return instructions.iterator();
	}
	
	public void addInstruction(String i) {
		this.instructions.add(i);
	}
	
	public Iterator<Parameter> getParameters() {
		return this.parameters.iterator();
	}
	
	public void addParameter(Parameter param) {
		this.parameters.add(param);
	}
	
	public String getReturnType() {
		return returnType;
	}
	
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	public void addThrow(String t) {
		this.throwz.add(t);
	}
	
	public Iterator<String> getThrows() {
		return this.throwz.iterator();
	}
	
}
	
