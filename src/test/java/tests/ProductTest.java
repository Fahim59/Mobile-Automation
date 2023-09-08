package tests;

import base.BaseClass;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.ConfigLoader;

public class ProductTest extends BaseClass {
    private ProductPage productPage;
    private HomePage homePage;

    String productName;
    String productPrice;

    @BeforeClass
    public void beforeClass() throws Exception {
        productName = new ConfigLoader().parseStringXML().get("product_name");
        productPrice = new ConfigLoader().parseStringXML().get("product_price");
    }

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        productPage = new ProductPage();
    }

    @Test
    public void validate_product_on_home_page() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        small_wait(1500);

        String title = homePage.getSLBTitle();
        softAssert.assertEquals(title, productName);

        String price = homePage.getSLBPrice();
        softAssert.assertEquals(price, productPrice);

        softAssert.assertAll();
    }

    @Test
    public void validate_product_on_product_page() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        small_wait(1500);

        homePage.clickSLBTitle();

        small_wait(2000);

        String title = productPage.getSLBTitle();
        softAssert.assertEquals(title, productName);

        String price = productPage.getSLBPrice();
        softAssert.assertEquals(price, productPrice);

        softAssert.assertAll();
    }

    @Test
    public void order_product() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        small_wait(1500);

        homePage.clickSLBTitle();

        small_wait(2000);

        String title = productPage.getSLBTitle();
        softAssert.assertEquals(title, productName);

        String price = productPage.getSLBPrice();
        softAssert.assertEquals(price, productPrice);

        Scroll_Down_Text_FindElement("Product Highlights");

        productPage.clickPlusButton();
        productPage.clickAddToCartButton();
        homePage.clickCartButton();

        softAssert.assertAll();
    }
}
