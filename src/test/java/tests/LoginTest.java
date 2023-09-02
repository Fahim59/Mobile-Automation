package tests;

import base.BaseClass;
import org.testng.annotations.*;
import pages.*;

public class LoginTest extends BaseClass {
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod()
    public void beforeMethod() {
        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    @Test
    public void login_with_valid_credentials() {
        homePage.clickMenuButton();
        homePage.clickLoginMenuButton();

        loginPage.login("bob@example.com","10203040");

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
