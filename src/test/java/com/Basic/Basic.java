package com.Basic;

import com.Base.BaseClass;

import java.net.MalformedURLException;

public class Basic extends BaseClass{
    public static void main(String[] args) throws MalformedURLException {
        Capabilities();
        //Test();
        //UIAutomator();
        CheckClickableObjects();
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
        FindElementByUIAutomator_Click("text","Expandable Lists");
        FindElementByUIAutomator_Click("text","1. Custom Adapter");
    }
}