package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.ConstantVariables;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayersPage extends LoginPage{
    private final By playerManagementElementBy = By.xpath("//a[contains(text(), 'Player management')]");
    private final By panelElement = By.className("panel-heading");
    private final By tableElementBy = By.id("payment-system-transaction-grid");
    private final By loaderElementBy = By.xpath("//div[@id='payment-system-transaction-grid' and @class='grid-view grid-view-loading']");

    private int columnIndex;
    private ArrayList<String> itemsTextBeforeSorting;

    public PlayersPage(WebDriver webDriver) {
        super (webDriver);
    }

    public void checkPlayersURL() {
        assertEquals(webDriver.getCurrentUrl(),
                ConstantVariables.PLAYERS_URL,
                "Link doesn't equal " +
                        ConstantVariables.PLAYERS_URL);
    }

    public void checkPlayerManagement() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(playerManagementElementBy));
    }

    public void checkPlayersTable() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(panelElement));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableElementBy));
    }

    public void clickColumn(String column) {
        By sortColumnElement = By.xpath("//a[contains(@class,\"sort-link\") and text() = \"" + column + "\"]");
        WebElement sortColumnTitle = webDriver.findElement(sortColumnElement);
        sortColumnTitle.click();
        waitingDisappearLoader();
    }

    public void getColumnIndex(String column) {
        By softLinkSelector = By.xpath("//th[.//a[contains(@class,\"sort-link\") and text() = \"" + column + "\"]]");
        By thSelector = By.tagName("th");
        WebElement softlink = webDriver.findElement(softLinkSelector);
        List<WebElement> softLinkTh = webDriver.findElements(thSelector);
        columnIndex = softLinkTh.indexOf(softlink) + 1;
    }

    public void collectColumnDataBeforeSorting (int columnIndex) {

        By itemsSelector = By.xpath("//div[@id=\"payment-system-transaction-grid\"]//tr[@class=\"odd\" or @class=\"even\"]//td[" + columnIndex + "]");
        ArrayList<WebElement> items = (ArrayList<WebElement>) webDriver.findElements(itemsSelector);
        itemsTextBeforeSorting = new ArrayList<>();

        for (WebElement name : items) {
            itemsTextBeforeSorting.add(name.getText());
        }
    }

    public void checkSorting() {
        assertTrue(compareDataAfterOrder(itemsTextBeforeSorting));
    }

    private boolean compareDataAfterOrder(ArrayList<String> itemsText) {
        By itemsSelector = By.xpath("//div[@id=\"payment-system-transaction-grid\"]//tr[@class=\"odd\" or @class=\"even\"]//td[" + columnIndex + "]");
        ArrayList<WebElement> items = (ArrayList<WebElement>) webDriver.findElements(itemsSelector);
        ArrayList<String> itemsTextAfterSorting = new ArrayList<>();
        for (WebElement element : items) {
            itemsTextAfterSorting.add(element.getText());
        }
        Collections.sort(itemsTextBeforeSorting);
        return itemsTextBeforeSorting.equals(itemsTextAfterSorting);
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void enterFieldWordForFilter(String filterWord, String field) {
        WebElement filterField = webDriver.findElement(By.xpath("//input[@name='PlayerSearch[" + field + "]']"));
        filterField.sendKeys(filterWord, Keys.ENTER);
        waitingDisappearLoader();
    }

    private void waitingDisappearLoader() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(webDriver.findElement(loaderElementBy)));
    }


    public void selectValueInExpandList(String valueInExpandList) {
        Select select = new Select(webDriver.findElement(By.xpath("//select[@name='pageSizePlayers']")));
        select.selectByValue(valueInExpandList);
        waitingDisappearLoader();
    }
}
