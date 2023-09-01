package tests;

import base.BaseClass;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseClass {
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod()
    public void beforeMethod() {
        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    @Test
    public void login_with_valid_credentials() throws InterruptedException {
        homePage.clickMenuButton();
        homePage.clickLoginMenuButton();

        loginPage.login("bob@example.com","10203040");

        small_wait(2000);

        homePage.logout();
    }

    @Test
    public void login_with_invalid_username() {
        loginPage.login("user","10203040");

        loginPage.clearField();
    }

    @Test
    public void login_with_invalid_password() {
        loginPage.login("bob@example.com","password");

        loginPage.clearField();
    }
}
