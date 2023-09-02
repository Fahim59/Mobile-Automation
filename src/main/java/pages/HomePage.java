package pages;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BaseClass {

    @AndroidFindBy(xpath = "//*[@content-desc='open menu']") private MobileElement menuBtn;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Log In']") private MobileElement loginMenuBtn;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Log Out']") private MobileElement logoutMenuBtn;

    @AndroidFindBy(id = "android:id/button1") private MobileElement logoutConfirmBtn;
    @AndroidFindBy(id = "android:id/button1") private MobileElement okBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Provided credentials do not match any user in this service.']") private MobileElement message;

    public HomePage clickMenuButton(){
        click_Element(menuBtn);
        return this;
    }

    public HomePage clickLoginMenuButton(){
        click_Element(loginMenuBtn);
        return this;
    }

    public HomePage clickLogoutMenuButton(){
        click_Element(logoutMenuBtn);
        return this;
    }

    public HomePage clickLogoutConfirmButton(){
        click_Element(logoutConfirmBtn);
        return this;
    }

    public HomePage clickOkButton(){
        click_Element(okBtn);
        return this;
    }

    public HomePage logout(){
        return clickMenuButton().clickLogoutMenuButton().clickLogoutConfirmButton().clickOkButton();
    }

    public String getErrorMessage(){
        return getText(message);
    }
}
