package com.Base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.aspectj.weaver.ast.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static void main(String[] args) throws MalformedURLException {}

    public static AndroidDriver<AndroidElement> driver;
    public static WebDriver driverW;

    public static AndroidDriver Capabilities() throws MalformedURLException {
        File folder = new File("src/main/resources","ApiDemos-debug.apk");

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Emulator");
        //cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        cap.setCapability(MobileCapabilityType.APP, folder.getAbsolutePath());

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        return driver;
    }

    public static void SmallWait() throws InterruptedException {Thread.sleep(2000);}
    public static void LongWait() throws InterruptedException {Thread.sleep(4000);}
    public static void ImplicitWait() {driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);}

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
}
