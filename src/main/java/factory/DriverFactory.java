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

        DesiredCapabilities cap = new DesiredCapabilities();

        if(device.equals("real")) {
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
            System.out.println("Android");
        }
        else if(device.equals("emulator")){
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Emulator");
            System.out.println("Emulator");
        }

        cap.setCapability(MobileCapabilityType.APP, folder.getAbsolutePath());
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        return driver;
    }

    public static AndroidDriver getDriver() {
        return driver;
    }
}