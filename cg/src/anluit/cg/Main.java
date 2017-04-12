package anluit.cg;

import java.util.Iterator;

import anluit.cg.generator.ClassGenerator;
import anluit.cg.generator.action.ActionGenerator;
import anluit.cg.generator.action.ActionGeneratorFactory;
import anluit.cg.input.ResultsInputReader;
import anluit.cg.output.OutputWriter;
import anluit.cg.results.DescriptionResult;
import anluit.cg.results.Result;
import anluit.cg.results.SentenceResult;
import anluit.cg.results.SuiteResult;
import anluit.cg.structures.Clazz;
import anluit.cg.structures.Method;

public class Main {

	public static void main(String[] args) {
		
		if (args.length < 2) {
			if (args.length == 1 && args[0].equals("-usage")) {
				System.out.println("cg [results filepath] [output filepath]");
			} else {
				System.out.println("Not enough parameters. See usage.");
			}
			return;
		}
		
		String resultFilepath = args[0];
		String generatedTestClassFilepath = args[1];
		
		ResultsInputReader rir = new ResultsInputReader();
		SuiteResult sr = rir.read(resultFilepath);
		sr.print();
		
		ClassGenerator gen = new ClassGenerator();
		
		Clazz c = new Clazz("anluit.tr", "public", "GeneratedTest2");
		
		// Add import
		c.addImport("android.support.test.uiautomator.UiSelector");
		c.addImport("android.support.test.uiautomator.UiDevice");
		c.addImport("android.support.test.InstrumentationRegistry");
		c.addImport("org.junit.Before");
		c.addImport("org.junit.Test");
		c.addImport("static org.junit.Assert.*");
		
		// Create getDevice() method
		Method getDeviceMethod = new Method("private", "UiDevice", "getDevice");
		getDeviceMethod.addInstruction("return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());");
		c.addMethod(getDeviceMethod);
		
		// Create setUp() method
		Method setUpMethod = new Method("public", "void", "setUp");
		setUpMethod.addThrow("Exception");
		setUpMethod.setAnnotation("@Before");
		setUpMethod.addInstruction("if (!getDevice().isScreenOn()) { getDevice().wakeUp(); }");
		setUpMethod.addInstruction("getDevice().pressHome();");
		c.addMethod(setUpMethod);
		
		Iterator<DescriptionResult> it1 = sr.getIterator();
		
		ActionGenerator ag;
		String[] agResults;
		
		while (it1.hasNext()) {
			DescriptionResult dr = it1.next();
			Iterator<SentenceResult> it2 = dr.getIterator();
			Method m = new Method("public", "void", "test_" + dr.getNoSpecialCharactersName());
			m.addThrow("Exception");
			m.setAnnotation("@Test");
			
			System.out.println("Description: " + dr.getName());

			while (it2.hasNext()) {
				SentenceResult ser = it2.next();
				System.out.println("\tSentence: " + ser.getSentence());
				
				ag = ActionGeneratorFactory.create(ser);
				
				if (ag == null) {
					System.out.println("\t\t\u001B[31m/!\\ Couldn't process this sentence.\u001B[0m");
					continue;
				}
				
				agResults = ag.generate();
				
				if (agResults != null && agResults.length > 0) {
					for(String result: agResults) {
						m.addInstruction(result);
					}
				} else {
					System.out.println("\t\t\u001B[31m/!\\ Couldn't generate code for this sentence.\u001B[0m");
				}

				Iterator<Result> it3 = ser.getIterator();
				while (it3.hasNext()) {
					Result res = it3.next();
					System.out.println("\t\t[" + res.getType() + " => " + res.getValue() + "]");
				}
			}
			
			c.addMethod(m);
		}
		
		String code = gen.generate(c);
		System.out.println();
		System.out.println("Generated code:");
		System.out.println(code);
		System.out.println();
		System.out.println("Operation completed.");
		
		OutputWriter.write(generatedTestClassFilepath, code);
	}
	
}
