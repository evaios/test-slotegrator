package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class AuthSteps {

    WebDriver webDriver = Hook.webDriver;
    private final LoginPage loginPage = new LoginPage(webDriver);
    private final HomePage homePage = new HomePage(webDriver);

    @When("I logged in with login {string} and password {string}")
    public void loggingWithLoginAndPassword(String login, String pass) {
        loginPage.fillLoginAndPassword(login, pass);
        loginPage.pressLoginButton();
    }

    @Given("I should be authorized")
    public void getHomePage() {
        homePage.checkHomeURL();
        homePage.waitForHomeElements();
    }

    @Given("Get the login page")
    public void getLoginPage() {
        loginPage.getLoginPage();
    }

    @Given("See login {string} on home page loaded")
    public void checkLoginOnHomePage(String login) {
        homePage.checkForLoginUser(login);
    }
}
