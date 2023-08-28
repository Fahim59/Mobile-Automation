package com.Basic;

import com.Base.BaseClass;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class General_Store extends BaseClass {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        Capabilities("emulator");

        fill_up_initial_info();                  //Tc_1
        //get_error_message();                   //Tc_2
        find_and_add_product_on_the_cart();      //Tc_3
        validate_mobile_gesture();               //Tc_4
        //hybrid_app_context();                    //Tc_5
    }

    public static void fill_up_initial_info() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Select_Scroll_Down("com.androidsample.generalstore:id/spinnerCountry","Bangladesh","text");

        FindElementByID_Details("com.androidsample.generalstore:id/nameField","Fahim");

        FindElementByUIAutomator_Click("text","Male");

        FindElementByID_Click("com.androidsample.generalstore:id/btnLetsShop");
    }

    public static void get_error_message() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Select_Scroll_Down("com.androidsample.generalstore:id/spinnerCountry","Bangladesh","text");

        FindElementByUIAutomator_Click("text","Male");

        FindElementByID_Click("com.androidsample.generalstore:id/btnLetsShop");

        String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");

        Assert.assertEquals("Please enter your name", toastMessage);
    }

    public static void find_and_add_product_on_the_cart() throws InterruptedException {
        double sum = 0;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String[] productNames = {"Nike SFB Jungle","Air Jordan 9 Retro"};

        for (String targetProductName : productNames) {
            boolean productFound = false;

            while (!productFound) {
                driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\""+targetProductName+"\").instance(0))"));

                int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

                for (int i = 0; i < count; i++) {
                    String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();

                    if (productName.equalsIgnoreCase(targetProductName)) {
                        double parsedValue = Double.parseDouble(driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText().replaceAll("[^0-9.]", ""));
                        sum = sum + parsedValue;

                        driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                        productFound = true;
                        break;
                    }
                }

                if (!productFound) {
                    break;
                }
            }
        }


        FindElementByID_Click("com.androidsample.generalstore:id/appbar_btn_cart");

        SmallWait(1500);

        for(int i = 0; i < productNames.length; i++){
            Assert.assertEquals(productNames[i], driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText());
        }

        double totalPrice = Double.parseDouble(driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().replaceAll("[^0-9.]", ""));
        Assert.assertEquals(sum,totalPrice);
    }

    public static void validate_mobile_gesture() throws InterruptedException {
        FindElementByUIAutomator_Click("text","Send me e-mails on discounts related to selected products in future");

        LongPressElementByXpath("//*[@text='Please read our terms of conditions']");

        SmallWait(1500);
        FindElementByUIAutomator_Click("text","CLOSE");

        FindElementByID_Click("com.androidsample.generalstore:id/btnProceed");
    }

    public static void hybrid_app_context() throws InterruptedException {
        SmallWait(5000);
        Set<String> contexts = driver.getContextHandles();

        for(String contextName : contexts){
            System.out.println(contextName);
        }

        driver.context("WEBVIEW_com.androidsample.generalstore");

        SmallWait(2000);

        driver.findElement(By.name("q")).sendKeys("Appium", Keys.ENTER);

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }
}
