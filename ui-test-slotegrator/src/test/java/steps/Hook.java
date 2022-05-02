package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;

public class Hook {
    public static WebDriver webDriver;

    @Before
    public void InitializeTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }


    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES)));
        }
        webDriver.quit();
    }
}
