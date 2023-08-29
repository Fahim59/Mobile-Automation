package hooks;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import utils.ConfigLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MyHooks {
    public static AndroidDriver<AndroidElement> driver;

    @BeforeClass()
    public AndroidDriver Capabilities() throws MalformedURLException {
        System.out.println("I am Before Class");

        String appName = new ConfigLoader().initializeProperty().getProperty("GeneralStoreApp");
        String device = new ConfigLoader().initializeProperty().getProperty("Device");

        File folder = new File("src/main/resources", appName);

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

    @AfterClass()
    public void afterClass() {
        System.out.println("I am After Class");
    }
}