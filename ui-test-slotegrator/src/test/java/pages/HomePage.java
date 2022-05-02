package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.ConstantVariables;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage {
    private final WebDriver webDriver;

    private final By playersPageElement = By.xpath("//div[@class='panel mini-box']//p[contains(text(),'Players')]");
    private final By buttonUserElement = By.cssSelector("li.nav-profile");
    private final By logoElement = By.className("logo");



    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void checkHomeURL() {
        assertThat(webDriver.getCurrentUrl(), equalTo(ConstantVariables.DASHBOARD_URL));
    }

    public void waitForHomeElements() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonUserElement));
        wait.until(ExpectedConditions.presenceOfElementLocated(logoElement));
    }

    public void checkForLoginUser(String login) {
        By loginElementBy = By.xpath("//span[contains(text(),'" + login + "')]");
        assertEquals(webDriver.findElement(loginElementBy).getText(), login, "Login is not correct");
    }

    public void goToPlayersPageByElement() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(playersPageElement));
        WebElement playersPage = webDriver.findElement(playersPageElement);
        playersPage.click();
        assertEquals("Dashboard - Player management", webDriver.getTitle(), "Not a Dashboard - Player management");
    }

    public void goToPlayersPage() {
        webDriver.navigate().to(ConstantVariables.PLAYERS_URL);
        assertEquals("Dashboard - Player management", webDriver.getTitle(), "Not a Dashboard - Player management");
    }
}
