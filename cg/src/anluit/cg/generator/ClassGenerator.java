package anluit.cg.generator;

import java.util.Iterator;

import anluit.cg.structures.Clazz;
import anluit.cg.structures.Method;
import anluit.cg.structures.Parameter;

public class ClassGenerator {
	
	public String generate(Clazz c) {
		if (c == null) {
			return null;
		}
		
		StringBuilder b = new StringBuilder();
		Iterator<String> imports = c.getImports();
		Iterator<Method> methods = c.getMethods();
		
		// Package
		b.append("package ").append(c.getPackage()).append(";").append("\n").append("\n");
		
		// Imports
		while (imports.hasNext()) {
			b.append("import ").append(imports.next()).append(";").append("\n");
		}
		
		b.append("\n");
		b.append(c.getVisibility()).append(" class ").append(c.getName()).append(" {").append("\n").append("\n");
		
		// Methods
		while (methods.hasNext()) {
			Method m = methods.next();
			
			// Annotation
			if (m.getAnnotation() != null) {
				b.append("\t").append(m.getAnnotation()).append("\n");
			}
			
			// Signature
			b.append("\t"); // one indentation before each method
			b.append(m.getVisibility());
			b.append(" ");
			b.append(m.getReturnType());
			b.append(" ");
			b.append(m.getName());
			b.append("(");
			
			Iterator<Parameter> parameters = m.getParameters();
			while (parameters.hasNext()) {
				Parameter p = parameters.next();
				b.append(p.getType()).append(" ").append(p.getName());
				if (parameters.hasNext()) {
					b.append(",");
				}
			}
			
			b.append(")");
			
			// Throws
			Iterator<String> throwz = m.getThrows();
			if (throwz.hasNext()) {
				b.append(" throws");
				while (throwz.hasNext()) {
					String t = throwz.next();
					b.append(" ").append(t);
					
					if (throwz.hasNext()) {
						b.append(",");
					}
				}
			}
			
			b.append(" { \n");
			
			// Body
			Iterator<String> instructions = m.getInstructions();
			while (instructions.hasNext()) {
				String instruction = instructions.next();
				b.append("\t").append("\t").append(instruction).append("\n");
			}
			
			b.append("\t").append("}").append("\n").append("\n");
		}
		
		b.append("}");
		
		return b.toString();
	}
}
