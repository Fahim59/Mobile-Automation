package com.Basic;

import com.Base.BaseClass;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.time.Duration;

public class Basic extends BaseClass{
    public static void main(String[] args) throws MalformedURLException {
        Capabilities();
        //Test();
        //UIAutomator();
        //CheckClickableObjects();
        Gestures();
    }

    public static void Test(){
        //Xpath = //tagName[@attribute = 'value']
        //Duplicate_Value = (//tagName)[2]

        FindElementByXpath_Click("//android.widget.TextView[@text = 'Preference']");

        FindElementByXpath_Click("//android.widget.TextView[@text = '3. Preference dependencies']");

        FindElementByID_Click("android:id/checkbox");
        FindElementByXpath_Click("//android.widget.TextView[@text = 'WiFi settings']");

        FindElementByID_Details("android:id/edit","FahimWifi");
        FindElementByXpath_Click("//android.widget.Button[@text = 'OK']");
    }

    public static void UIAutomator(){
        //FindElementByUIAutomator_Click("text","Text");

        FindElementByUIAutomator_Click("text","Views");
        FindElementByUIAutomator_Click("text","Animation");
    }

    public static void CheckClickableObjects(){
        //driver.findElementsByAndroidUIAutomator("new UiSelector().property(value)");

        System.out.println(driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size());
    }

    public static void Gestures(){
        //======================== Press and Hold ========================//

        FindElementByUIAutomator_Click("text","Views");

        TouchAction tap = new TouchAction<>(driver);

        WebElement expandList = driver.findElement(By.xpath("//android.widget.TextView[@text = 'Expandable Lists']"));
        tap.tap(new TapOptions().withElement(ElementOption.element(expandList))).perform();

        TapElementByXpath("//android.widget.TextView[@text = '1. Custom Adapter']");

        WebElement peopleName = driver.findElement(By.xpath("//android.widget.TextView[@text = 'People Names']"));
        tap.longPress(LongPressOptions.longPressOptions().withElement(ElementOption
                .element(peopleName)).withDuration(Duration.ofSeconds(2))).release().perform();
    }
}