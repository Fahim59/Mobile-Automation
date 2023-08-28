package com.Base;

import com.utils.ConfigLoader;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class BaseClass {

    public static void main(String[] args) throws MalformedURLException,InterruptedException{}

    public static AndroidDriver<AndroidElement> driver;

    public static AndroidDriver Capabilities(String device) throws MalformedURLException {
        String appName = new ConfigLoader().initializeProperty().getProperty("GeneralStoreApp");

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

    public static AndroidDriver Browser_Capabilities() {
        try{
            DesiredCapabilities cap = new DesiredCapabilities();

            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");

            cap.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
            cap.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, "C:\\Users\\Mustafizur Rahman\\Downloads\\chromedriver-win32\\chromedriver.exe");

            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return driver;
    }

    public static void SmallWait(int sec) throws InterruptedException {Thread.sleep(sec);}

    //=====================================================================================================//
    public static void FindElementByXpath(String xpath){driver.findElement(By.xpath(xpath));}
    public static void FindElementByXpath_Click(String xpath){driver.findElement(By.xpath(xpath)).click();}
    public static void FindElementByXpath_Details(String xpath, String details){
        AndroidElement element = driver.findElement(By.xpath(xpath));
        String text = element.getAttribute("text");

        if(text.isEmpty()){element.sendKeys(details);}
        else{element.clear();element.sendKeys(details);}
    }

    public static void FindElementByID_Click(String id){driver.findElement(By.id(id)).click();}
    public static void FindElementByID_Details(String id, String details){
        AndroidElement element = driver.findElement(By.id(id));
        String text = element.getAttribute("text");

        if(text.isEmpty()){element.sendKeys(details);}
        else{element.clear();element.sendKeys(details);}
    }

    public static void FindElementByUIAutomator_Click(String attribute, String value){driver.findElementByAndroidUIAutomator(""+attribute+"(\""+value+"\")").click();}

    public static void TapElementByXpath(String xpath){
        TouchAction tap = new TouchAction<>(driver);

        WebElement element = driver.findElement(By.xpath(xpath));
        tap.tap(new TapOptions().withElement(ElementOption.element(element))).perform();
    }

    public static void TapElementById(String id){
        TouchAction tap = new TouchAction<>(driver);

        WebElement element = driver.findElement(By.id(id));
        tap.tap(new TapOptions().withElement(ElementOption.element(element))).perform();
    }

    public static void LongPressElementByXpath(String xpath){
        TouchAction tap = new TouchAction<>(driver);

        WebElement peopleName = driver.findElement(By.xpath(xpath));
        tap.longPress(LongPressOptions.longPressOptions().withElement(ElementOption
                .element(peopleName)).withDuration(Duration.ofSeconds(3))).release().perform();
    }

    public static void SwipeElementByXpath(String fromXpath, String toXpath){
        TouchAction tap = new TouchAction<>(driver);

        WebElement from = driver.findElement(By.xpath(fromXpath));
        WebElement to = driver.findElement(By.xpath(toXpath));

        tap.longPress(LongPressOptions.longPressOptions().withElement(ElementOption
                        .element(from)).withDuration(Duration.ofSeconds(1))).moveTo(ElementOption.element(to))
                .release().perform();
    }

    public static void Drag_DropElementByXpath(String fromXpath, String toXpath){
        TouchAction tap = new TouchAction<>(driver);

        WebElement from = driver.findElement(By.xpath(fromXpath));
        WebElement to = driver.findElement(By.xpath(toXpath));

        tap.longPress(element(from)).moveTo(element(to)).release().perform();
    }

    //=====================================================================================================//
    public static void Scroll_Down_Xpath_FindElement(String xpath) throws InterruptedException {
        SmallWait(1500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(xpath));
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void Scroll_Down() throws InterruptedException {
        SmallWait(1500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)", "");
    }

    public static void Scroll_Down_Text_FindElement(String text) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
    }
    //=====================================================================================================//
    public static void Select_Scroll_Down(String id, String text, String attribute) {
        TouchAction tap = new TouchAction<>(driver);

        WebElement element = driver.findElement(By.id(id));
        tap.tap(new TapOptions().withElement(ElementOption.element(element))).perform();

        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");

        driver.findElementByAndroidUIAutomator(""+attribute+"(\""+text+"\")").click();
    }
}
