package anluit.cg.test.results;

import anluit.cg.results.DescriptionResult;
import junit.framework.*;

public class DescriptionResultTest extends TestCase {

	public void testGetNoSpecialCharactersNameNull() {
		DescriptionResult dr = new DescriptionResult(null);
		assertNull(dr.getNoSpecialCharactersName());
	}
	
	public void testGetNoSpecialCharactersNameEmptyString() {
		DescriptionResult dr = new DescriptionResult("");
		assertEquals(dr.getNoSpecialCharactersName(), "");
	}
	
	public void testGetNoSpecialCharactersNameSuccess() {
		DescriptionResult dr = new DescriptionResult("test-1 2.3,4.");
		assertEquals(dr.getNoSpecialCharactersName(), "test1234");
	}
	
}
