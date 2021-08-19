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

import java.time.temporal.TemporalAmount;
import java.util.concurrent.TimeUnit;

public class Stepdefs {

    WebDriver driver;
    String url = "https://parabank.parasoft.com/parabank/index.htm";
    String username = "john";
    String password = "demo";
    Scenario scenario;

    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void cleanUp(){
       // driver.quit();
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

    @Given("user clicked on link Open New Account")
    public void user_clicked_on_link_open_new_account(){

        driver.findElement(By.linkText("Open New Account")).click();
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

    @When("user clicks on Button {string}")
    public void user_clicks_on_button_Open_New_Account(String string) throws InterruptedException {
        Thread.sleep(5000);
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

    @Given("user clicked on link {string}")
    public void user_clicked_on_link(String string) {
        driver.findElement(By.linkText("Transfer Funds")).click();
    }
    @When("User enters the amount as {string} to be transferred")
    public void user_enters_the_amount_as_to_be_transferred(String int1) throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.findElement(By.id("amount")).sendKeys(int1);
    }

    @When("user selects sender account number and recipient account number")
    public void user_selects_sender_account_number_and_recipient_account_number() throws InterruptedException {
        Thread.sleep(2000);

        WebElement dropdownAccNumber1 = driver.findElement(By.id("fromAccountId"));
        Select selectAccType = new Select(dropdownAccNumber1);
        selectAccType.selectByIndex(0);

        Thread.sleep(2000);
        WebElement dropdownAccNumber2 = driver.findElement(By.id("toAccountId"));
        Select selectAccNumber = new Select(dropdownAccNumber2);
        selectAccNumber.selectByIndex(0);
    }

    @When("User clicks on {string} button")
    public void user_clicks_on_button(String string) {
        driver.findElement(By.xpath("//input[@value='Transfer']")).click();
    }
    @Then("{string} message will be displayed.")
    public void message_will_be_displayed(String string) {
        WebElement element = driver.findElement(By.xpath("//h1[text()='Transfer Complete!']"));
        Assert.assertEquals(element.isDisplayed(),true,"Funds Transfer succeeded Message");
    }
    @Given("user clicked on the link {string}")
    public void user_clicked_on_the_link(String string)

    {
        driver.findElement(By.linkText("Bill Pay")).click();
    }

    @When("user enters Payee name as {string} and address as {string}")
    public void user_enters_payee_name_as_and_address_as(String PName, String address) {
        driver.findElement(By.name("payee.name")).sendKeys(PName);
        driver.findElement(By.name("payee.address.street")).sendKeys(address);
    }
    @When("user enters city as {string} and state as {string}")
    public void user_enters_city_as_and_state_as(String city, String state) {
      driver.findElement(By.name("payee.address.city")).sendKeys(city);
        driver.findElement(By.name("payee.address.state")).sendKeys(state);
    }
    @When("user enters Zip code as {string} and phone no. as {string}")
    public void user_enters_zip_code_as_and_contact_no_as(String zip_code, String phone_number) {
        driver.findElement(By.name("payee.address.zipCode")).sendKeys(zip_code);
        driver.findElement(By.name("payee.phoneNumber")).sendKeys(phone_number);
    }
    @When("user enters account number as {string} and Verify account as {string}")
    public void user_enters_account_number_as_and_verify_account_as(String accNum, String Confirm_acc) {
        driver.findElement(By.name("payee.accountNumber")).sendKeys(accNum);
        driver.findElement(By.name("verifyAccount")).sendKeys(Confirm_acc);
    }

    @When("user enters the amount as {string} and enters existing account number")
    public void user_enters_the_amount_as_and_enters_existing_account_number(String amt) throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.name("amount")).sendKeys(amt);

        Thread.sleep(5000);

        WebElement dropdownAccNum =driver.findElement(By.name("fromAccountId"));
        Select selectAccNumber = new Select(dropdownAccNum);
        selectAccNumber.selectByIndex(0);
    }
    @When("user clicks on the {string} Button")
    public void user_clicks_on_the_button(String string) {
            driver.findElement(By.xpath("//input[@value='Send Payment']")).click();
        }

    @Then("message willbe displayed as {string}")
    public void message_willbe_displayed_as(String string) {
        WebElement element = driver.findElement(By.xpath("//h1[text()='Bill Payment Complete']"));
        Assert.assertEquals(element.isDisplayed(),true,"Payment of bill is successful Message");
    }

}