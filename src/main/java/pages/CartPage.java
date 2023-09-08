package pages;

import base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends BaseClass {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Sauce Labs Backpack']") private MobileElement slbTitle;
    @AndroidFindBy(xpath = "//*[@content-desc = 'product price'][1]") private MobileElement slbPrice;

    @AndroidFindBy(xpath = "//*[@content-desc = 'total number'][1]") private MobileElement totalItem;
    @AndroidFindBy(xpath = "//*[@content-desc = 'total price'][1]") private MobileElement totalAmount;

    @AndroidFindBy(xpath = "//*[@content-desc = 'Proceed To Checkout button'][1]") private MobileElement proceedBtn;

    public String getSLBTitle(){
        return getText(slbTitle);
    }

    public String getSLBPrice(){
        return getText(slbPrice);
    }

    public String getTotalItem(){
        return getText(totalItem);
    }

    public String getTotalPrice(){
        return getText(totalAmount);
    }

    public CartPage clickProceedButton(){
        click_Element(proceedBtn);
        return this;
    }
}
