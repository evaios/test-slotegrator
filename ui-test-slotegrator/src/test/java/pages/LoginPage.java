package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.ConstantVariables;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage {
    protected WebDriver webDriver;
    private final By loginElementBy = By.id("UserLogin_username");
    private final By passwordElementBy = By.id("UserLogin_password");
    private final By loginButtonElement = By.xpath("//input[@type=\"submit\"]");

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void getLoginPage() {
        webDriver.navigate().to(ConstantVariables.LOGIN_URL);
        assertEquals("Dashboard - Casino", webDriver.getTitle());
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButtonElement));
    }


    public void fillLoginAndPassword(String login, String pass) {
        WebElement loginField = webDriver.findElement(loginElementBy);
        WebElement passwordField = webDriver.findElement(passwordElementBy);

        loginField.sendKeys(login);
        passwordField.sendKeys(pass);
    }

    public void pressLoginButton() {
        WebElement loginButton = webDriver.findElement(loginButtonElement);

        loginButton.click();
    }


}
