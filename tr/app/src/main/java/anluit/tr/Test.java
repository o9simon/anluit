package anluit.tr;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by simon on 15-11-26.
 *
 * This is a simple UI test to make sure JUnit and UiAutomator are working properly.
 * This test is coded to work on my own phone. Will not work on every phone.
 */
public class Test {

    private UiDevice getDevice() {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Before
    public void setUp() throws Exception {
        // Turn the screen on if it's off
        if (!getDevice().isScreenOn()) {
            getDevice().wakeUp();
        }

        // Start from the home screen
        getDevice().pressHome();
    }

    @org.junit.Test
    public void testToggleWifi() throws Exception {
        (getDevice().findObject(new UiSelector().text("Settings"))).click();

        // Find switch
        UiObject wifiSwitch = getDevice().findObject(new UiSelector().className("android.widget.Switch").instance(0));
        wifiSwitch.click();
    }
}
