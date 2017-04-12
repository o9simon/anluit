package anluit.cg.structures;

import java.util.ArrayList;
import java.util.Iterator;

public class Clazz {
	
	private String pkg;
	private ArrayList<String> imports = new ArrayList<String>();
	private String visibility;
	private String name;
	private ArrayList<Method> methods = new ArrayList<Method>();
	
	public Clazz(String pkg, String visibility, String name) {
		this.pkg = pkg;
		this.visibility = visibility;
		this.name = name;
	}
	
	public void addMethod(Method m) {
		methods.add(m);
	}
	
	public void addImport(String i) {
		imports.add(i);
	}
	
	public String getPackage() {
		return pkg;
	}

	public void setPackage(String pkg) {
		this.pkg = pkg;
	}

	public Iterator<String> getImports() {
		return imports.iterator();
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Iterator<Method> getMethods() {
		return methods.iterator();
	}
	
}
