package Stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class Stepdefs {

    WebDriver driver;
    String url = "https://parabank.parasoft.com/parabank/index.html";
    String username = "John";
    String password = "demo";
    Scenario scenario;

    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void cleanUp(){
        driver.quit();
    }

    @Given("user opened the browser")
    public void user_opened_the_browser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Given("user navigated to the application url")
    public void user_navigated_to_the_application_url() {
        driver.get(url);
    }

    @When("user enter the username as {string} and password as {string} and click the login button")
    public void user_enter_the_username_as_and_password_as_and_click_the_login_button(String username, String password)
    {

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
    }

    @Then("user is able to login in the application")
    public void user_is_able_to_login_in_the_application()
    {
        Assert.assertEquals("ParaBank | Accounts Overview",driver.getTitle());
        boolean actualTableDisplayed = driver.findElement(By.id("accountTable")).isDisplayed();
        Assert.assertEquals(true,actualTableDisplayed);
    }


   @Given("User is logged in")
   public void User_logged_In()
   {
        user_opened_the_browser();
        user_navigated_to_the_application_url();
        user_enter_the_username_as_and_password_as_and_click_the_login_button(username,password);
    }

    @Given("user clicked on link {string}")
    public void user_clicked_on_link(String linkname){
        driver.findElement(By.linkText(linkname)).click();
    }

    @When("user select account as {string} and account number as{string}")
    public void user_select_account_and_account_number_as(String type, String AccNumber)
    {
        WebElement dropdownAccType = driver.findElement(By.id("type"));
        Select selectAccType = new Select(dropdownAccType);
        selectAccType.selectByVisibleText(type);

        WebElement dropdownAccNumber = driver.findElement(By.id("fromAccountId"));
        Select selectAccNumber = new Select(dropdownAccNumber);
        selectAccNumber.selectByVisibleText("AccNumber");

    }

    @When("user select account as {string} and account number")
    public void user_select_account_as_and_account_number_as(String type)
    {
        WebElement dropdownAccType = driver.findElement(By.id("type"));
        Select selectAccType = new Select(dropdownAccType);
        selectAccType.selectByVisibleText(type);

        WebElement dropdownAccNumber = driver.findElement(By.id("fromAccountId"));
        Select selectAccNumber = new Select(dropdownAccNumber);
        selectAccNumber.selectByIndex(0);

    }

    @When("user clicks on Button Open New Account")
    public void user_clicks_on_button_Open_New_Account()
    {
        driver.findElement(By.xpath("//input[@type='submit' and @value='Open New Account']")).click();
    }

    @Then("{string} message is displayed")
    public void message_is_displayed(String string) {
        WebElement element = driver.findElement(By.xpath("//h1[text()='Account Opened!']"));
        Assert.assertEquals(element.isDisplayed(),true,"Account Success Message");
    }

    @Then("a new account number is generated")
    public void a_new_account_number_is_generated() {
       WebElement element = driver.findElement(By.id("newAccountId"));
       String account_number= element.getText();
       Assert.assertEquals(element.isDisplayed(),true,"New Account Number link");
       scenario.log("Newly Generated Account is: " +account_number);
    }

}