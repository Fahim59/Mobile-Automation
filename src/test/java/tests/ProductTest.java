package tests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

public class ProductTest extends BaseClass {
    private HomePage homePage;
    private ProductPage productPage;

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        productPage = new ProductPage();
    }

    @Test
    public void validate_product_on_products_page() throws InterruptedException {
        small_wait(1500);
        String title = homePage.getSLBTitle();
        String price = homePage.getSLBPrice();

        homePage.clickSLBTitle();

        small_wait(1000);

        Assert.assertEquals(title, productPage.getSLBTitle());
        Assert.assertEquals(price, productPage.getSLBPrice());
    }
}
