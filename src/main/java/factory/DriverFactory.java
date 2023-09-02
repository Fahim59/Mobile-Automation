package factory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    protected static AndroidDriver<AndroidElement> driver;

    public static AndroidDriver initializeDriver() throws MalformedURLException {
        String appName = new ConfigLoader().initializeProperty().getProperty("Application");
        String device = new ConfigLoader().initializeProperty().getProperty("Device");
        File folder = new File("src/test/resources", appName);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

        if(device.equals("real")) {
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
            //caps.setCapability(MobileCapabilityType.UDID, "");
            System.out.println("Android");
        }
        else if(device.equals("emulator")){
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Emulator");
            caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            caps.setCapability("avd", "Emulator");
            caps.setCapability("avdLaunchTimeout", 180000);
            System.out.println("Emulator");
        }

        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.APP, folder.getAbsolutePath());

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

        return driver;
    }
}