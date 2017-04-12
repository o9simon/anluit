package anluit.tr;

import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.InstrumentationRegistry;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest2 {

	private UiDevice getDevice() { 
		return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
	}

	@Before
	public void setUp() throws Exception { 
		if (!getDevice().isScreenOn()) { getDevice().wakeUp(); }
		getDevice().pressHome();
	}

	@Test
	public void test_calculator() throws Exception { 
		(getDevice().findObject(new UiSelector().textMatches("(?i)(calculator)"))).click();
		(getDevice().findObject(new UiSelector().descriptionMatches("(?i)(7)").className("android.widget.ImageButton"))).click();
		(getDevice().findObject(new UiSelector().descriptionMatches("(?i)(plus)").className("android.widget.ImageButton"))).click();
		(getDevice().findObject(new UiSelector().descriptionMatches("(?i)(3)").className("android.widget.ImageButton"))).click();
		(getDevice().findObject(new UiSelector().descriptionMatches("(?i)(equal)").className("android.widget.ImageButton"))).click();
		assertTrue((getDevice().findObject(new UiSelector().textContains("=10").className("android.widget.EditText"))).exists());
	}

	@Test
	public void test_writememo() throws Exception { 
		(getDevice().findObject(new UiSelector().textMatches("(?i)(memo)"))).click();
		(getDevice().findObject(new UiSelector().descriptionMatches("(?i)(create memo)").className("android.widget.TextView"))).click();
		(getDevice().findObject(new UiSelector().className("android.widget.EditText"))).setText("asdf");
		(getDevice().findObject(new UiSelector().textMatches("(?i)(save)"))).click();
	}

	@Test
	public void test_junit() throws Exception { 
		assertEquals("1", "1");
		assertTrue(2 > 1);
		assertTrue(1 < 100);
		assertEquals("banana", "banana");
		assertTrue(3 > 2);
		assertEquals("2", "2");
	}

	@Test
	public void test_togglewifi() throws Exception { 
		(getDevice().findObject(new UiSelector().textMatches("(?i)(settings)"))).click();
		(getDevice().findObject(new UiSelector().textMatches("(?i)(wi-fi)"))).click();
		(getDevice().findObject(new UiSelector().className("android.widget.Switch"))).click();
		assertTrue((getDevice().findObject(new UiSelector().className("android.widget.Switch").text("OFF"))).exists());
	}

	@Test
	public void test_togglewifi2() throws Exception { 
		(getDevice().findObject(new UiSelector().textMatches("(?i)(settings)"))).click();
		(getDevice().findObject(new UiSelector().textMatches("(?i)(wi-fi)"))).click();
		(getDevice().findObject(new UiSelector().className("android.widget.Switch"))).click();
		assertTrue((getDevice().findObject(new UiSelector().className("android.widget.Switch").text("OFF"))).exists());
	}

	@Test
	public void test_togglebluetooth() throws Exception { 
		(getDevice().findObject(new UiSelector().textMatches("(?i)(settings)"))).click();
		(getDevice().findObject(new UiSelector().textMatches("(?i)(bluetooth)"))).click();
		(getDevice().findObject(new UiSelector().className("android.widget.Switch"))).click();
	}

}
