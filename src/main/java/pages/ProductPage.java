package pages;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductPage extends BaseClass {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Sauce Labs Backpack']") private MobileElement slbTitle;
    @AndroidFindBy(xpath = "//*[@content-desc='product price']") private MobileElement slbPrice;

    public String getSLBTitle(){
        return getText(slbTitle);
    }

    public String getSLBPrice(){
        return getText(slbPrice);
    }
}
