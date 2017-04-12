package anluit.cg.test.generator;

import anluit.cg.generator.ClassGenerator;
import junit.framework.*;

public class ClassGeneratorTest extends TestCase {

	public void testGenerateNull() {
		ClassGenerator gen = new ClassGenerator();
		assertNull(gen.generate(null));
	}
	
}
