package tests;

import base.BaseClass;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;

public class General_Store extends BaseClass {

    @Test
    public void login_with_valid_credentials() throws InterruptedException {
        Thread.sleep(2000);
        MobileElement menuBtn = driver.findElement(MobileBy.xpath("//*[@content-desc='open menu']"));
        menuBtn.click();

        Thread.sleep(2000);
        MobileElement loginMenuBtn = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text = 'Log In']"));
        loginMenuBtn.click();

        Thread.sleep(2000);
        MobileElement userNameField = driver.findElement(MobileBy.xpath("//*[@content-desc='Username input field']"));
        MobileElement passwordField = driver.findElement(MobileBy.xpath("//*[@content-desc='Password input field']"));
        MobileElement loginBtn = driver.findElement(MobileBy.xpath("//*[@content-desc='Login button']"));

        userNameField.sendKeys("bob@example.com");
        passwordField.sendKeys("10203040");
        loginBtn.click();

        Thread.sleep(2000);
        menuBtn.click();

        Thread.sleep(2000);
        MobileElement logoutMenuBtn = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text = 'Log Out']"));
        logoutMenuBtn.click();

        Thread.sleep(2000);
        MobileElement logoutConfirmBtn = driver.findElement(MobileBy.id("android:id/button1"));
        logoutConfirmBtn.click();

        Thread.sleep(2000);
        MobileElement okBtn = driver.findElement(MobileBy.id("android:id/button1"));
        okBtn.click();
    }

    @Test
    public void login_with_invalid_username() throws InterruptedException {
        Thread.sleep(2000);
        MobileElement userNameField = driver.findElement(MobileBy.xpath("//*[@content-desc='Username input field']"));
        MobileElement passwordField = driver.findElement(MobileBy.xpath("//*[@content-desc='Password input field']"));
        MobileElement loginBtn = driver.findElement(MobileBy.xpath("//*[@content-desc='Login button']"));

        userNameField.sendKeys("user");
        passwordField.sendKeys("10203040");
        loginBtn.click();

        userNameField.clear();
        passwordField.clear();
    }

    @Test
    public void login_with_invalid_password() throws InterruptedException {
        Thread.sleep(2000);
        MobileElement userNameField = driver.findElement(MobileBy.xpath("//*[@content-desc='Username input field']"));
        MobileElement passwordField = driver.findElement(MobileBy.xpath("//*[@content-desc='Password input field']"));
        MobileElement loginBtn = driver.findElement(MobileBy.xpath("//*[@content-desc='Login button']"));

        userNameField.sendKeys("bob@example.com");
        passwordField.sendKeys("password");
        loginBtn.click();
    }
}
