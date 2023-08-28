package hooks;

import factory.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeClass;
import pages.BasePage;
import utils.ConfigLoader;

import java.net.MalformedURLException;

public class MyHooks extends BasePage{
    public static AndroidDriver driver;

    @BeforeClass()
    public void launch_browser() throws MalformedURLException {
        driver = DriverFactory.initializeDriver(new ConfigLoader().initializeProperty().getProperty("GeneralStoreApp"));
    }
}