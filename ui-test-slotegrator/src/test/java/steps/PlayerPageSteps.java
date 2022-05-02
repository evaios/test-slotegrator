package steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import pages.HomePage;
import pages.LoginPage;
import pages.PlayersPage;
public class PlayerPageSteps {

    WebDriver webDriver = Hook.webDriver;
    private final LoginPage loginPage = new LoginPage(webDriver);
    private final HomePage homePage = new HomePage(webDriver);
    private final PlayersPage playersPage = new PlayersPage(webDriver);

    @Given("I logged in with login {string} and password {string} on HomePage")
    public void loggingWithLoginAndPasswordOnHomePage(String login, String password) {

        loginPage.getLoginPage();
        loginPage.fillLoginAndPassword(login, password);
        loginPage.pressLoginButton();

        homePage.waitForHomeElements();
        homePage.checkHomeURL();
        homePage.checkForLoginUser(login);

    }

    @Then("Go to the players page")
    public void loadPlayersPage() {
        homePage.goToPlayersPage();
        playersPage.checkPlayerManagement();
        playersPage.checkPlayersURL();
    }

    @When("I click on Players in HomePage")
    public void iClickOnTheTabInMenus() {
        homePage.goToPlayersPageByElement();
    }


    @And("Information players table loaded")
    public void playersTablePlayersLoaded() {
        playersPage.checkPlayersTable();
    }

    @Then("I click on the column {string} for order")
    public void clickColumnForOrder(String column) {
        playersPage.clickColumn(column);
    }

    @Then("I should be at the players page")
    public void iShouldBeAtThePlayersPage() {
        playersPage.checkPlayerManagement();
        playersPage.checkPlayersURL();
    }

    @And("I add filter {string} in field {string}")
    public void iAddFilterInField(String filterWord, String field) {
        playersPage.enterFieldWordForFilter(filterWord, field);
    }

    @When("I select {string} in expand list")
    public void iSelectInExpandList(String valueInExpandList) {
        playersPage.selectValueInExpandList(valueInExpandList);
    }

    @And("Check data in column {string} will be ordered")
    public void dataInColumnWillBeOrdered(String column) {

        playersPage.getColumnIndex(column);
//        playersPage.collectColumnDataBeforeSorting(playersPage.getColumnIndex());
        playersPage.checkSorting();

    }


    @And("I collect data before sorting in column {string}")
    public void iCollectDataBeforeSortingInColumn(String column) {
        playersPage.getColumnIndex(column);
        playersPage.collectColumnDataBeforeSorting(playersPage.getColumnIndex());
    }
}