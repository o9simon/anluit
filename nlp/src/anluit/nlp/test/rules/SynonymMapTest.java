package anluit.nlp.test.rules;

import anluit.nlp.rules.SynonymMap;
import junit.framework.*;

public class SynonymMapTest extends TestCase {

	public void testGetKeySynonymNull() {
		SynonymMap syn = new SynonymMap();
		assertEquals(syn.getKeySynonym(null), null);
	}
	
	public void testGetKeySynonymNotExist() {
		SynonymMap syn = new SynonymMap();
		assertEquals(syn.getKeySynonym("doesnotexist"), "doesnotexist");
	}
	
	public void testGetKeySynonymExist() {
		SynonymMap syn = new SynonymMap();
		syn.add("click", new String[]{"tap", "press"});
		assertEquals(syn.getKeySynonym("tap"), "click");
		assertEquals(syn.getKeySynonym("press"), "click");
	}
	
}
