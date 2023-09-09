package base;

import factory.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ConfigLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseClass {
    protected static AndroidDriver<AndroidElement> driver;

    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    public BaseClass(){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @BeforeTest()
    public void initialize_driver() throws Exception {
        driver = DriverFactory.initializeDriver();

        logger.info("initialize_driver");
    }

    @BeforeMethod
    public void beforeMethod() {
        getDriver().startRecordingScreen();
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result) {
        String media = getDriver().stopRecordingScreen();

        if(result.getStatus() == 2){
            String videoPath = "Video" + File.separator + new ConfigLoader().initializeProperty().getProperty("Platform")
                    + "_" + new ConfigLoader().initializeProperty().getProperty("Device") + File.separator + dateTime() + File.separator
                    + result.getTestClass().getRealClass().getSimpleName();

            File videoDir = new File(videoPath);
            //if(!videoDir.exists()) { videoDir.mkdirs(); }
            synchronized(videoDir){
                if(!videoDir.exists()) { videoDir.mkdirs(); }
            }

            try {
                FileOutputStream stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
                stream.write(Base64.decodeBase64(media));
                System.out.println(media.length());
                stream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public AndroidDriver getDriver(){
        return driver;
    }

    public void small_wait(int msec) throws InterruptedException {
        Thread.sleep(msec);
    }

    public void wait_for_visibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void send_Keys(MobileElement element, String txt) {
        wait_for_visibility(element);
        String value = element.getAttribute("text");

        if (value.isEmpty()) {
            element.sendKeys(txt);
        }
        else {
            element.clear();
            element.sendKeys(txt);
        }
    }

    public void click_Element(MobileElement element) {
        wait_for_visibility(element);
        element.click();
    }

    public String getText(MobileElement element) {
        wait_for_visibility(element);
        return element.getAttribute("text");
    }

    public static void Scroll_Down_Text_FindElement(String text) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
    }

    public String dateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @AfterTest()
    public void quit_driver() {
        try {
            //driver.quit();
            //Runtime.getRuntime().exec("adb emu kill");

            File logFile = new File("Log Result/test.log");
            File outputFile = new File("Log Result/test_output.txt");

            String logContents = FileUtils.readFileToString(logFile, "UTF-8");
            FileUtils.writeStringToFile(outputFile, logContents, "UTF-8");
        }
        catch (Exception e) {
            System.out.println("Log save failed" +e);
        }
    }
}
