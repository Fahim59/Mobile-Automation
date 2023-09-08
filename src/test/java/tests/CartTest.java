package tests;

import base.BaseClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import utils.ConfigLoader;

import java.util.regex.Pattern;

public class CartTest extends BaseClass {
    private ProductPage productPage;
    private CartPage cartPage;

    String productName;
    String productPrice;

    @BeforeClass
    public void beforeClass() throws Exception {
        productName = new ConfigLoader().parseStringXML().get("product_name");
        productPrice = new ConfigLoader().parseStringXML().get("product_price");
    }

    @BeforeMethod
    public void beforeMethod() {
        cartPage = new CartPage();
    }

    @Test
    public void check_product_details() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        small_wait(1500);

        String title = cartPage.getSLBTitle();
        softAssert.assertEquals(title, productName);

        String price = cartPage.getSLBPrice();
        softAssert.assertEquals(price, productPrice);

        softAssert.assertEquals(price, productPrice);

        String unitPrice = price.replaceAll("[^0-9.]", "");
        String totalItem = cartPage.getTotalItem().replaceAll("[^0-9.]", "");
        String totalPrice = cartPage.getTotalPrice().replaceAll("[^0-9.]", "");

        String tPrice = String.valueOf(Double.parseDouble(unitPrice) * Double.parseDouble(totalItem));

        softAssert.assertEquals(tPrice, totalPrice);

        cartPage.clickProceedButton();

        softAssert.assertAll();
    }
}
