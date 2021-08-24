package Stepdefs;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.DriverFactory;

import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log4j2
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

    @BeforeStep
    public void beforeEachStep(){
        scenario.log("Executed before step");
    }
    @AfterStep
    public void afterEachStep(){
        if(!(driver==null)) {
            TakesScreenshot scrnShot = (TakesScreenshot) driver;
            byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/png", "Failed step names:" + scenario.getName());
            scenario.log("Executed after step");
        }
        scenario.log("Executed after step");
        log.debug("Each step hook is executed, screen shot is taken");
    }

    @Given("user opened the browser")
    public void user_opened_the_browser() {
       String browserName= System.getProperty("browser");
        driver =  DriverFactory.createInstance("browser");
        driver = new ChromeDriver();
        log.debug("browser is opened");
        driver.manage().window().maximize();
        log.debug("browser is maximised");
        driver.manage().deleteAllCookies(); //delete all cookies
        log.debug("deleted cookies");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Given("user navigated to the application url")
    public void user_navigated_to_the_application_url() {
        driver.get(url);
        log.debug("url navigated");
    }

    @When("user enter the username as {string} and password as {string} and click the login button")
    public void user_enter_the_username_as_and_password_as_and_click_the_login_button(String username, String password)
    {

        driver.findElement(By.name("username")).sendKeys(username);
        log.debug("username entered: "+username);
        driver.findElement(By.name("password")).sendKeys(password);
        log.debug("password entered: "+password);
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
        log.debug("login button clicked");
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
    public void user_select_account_as_and_account_number_as(String type) throws InterruptedException {
        WebElement dropdownAccType = driver.findElement(By.id("type"));
        Select selectAccType = new Select(dropdownAccType);
        selectAccType.selectByVisibleText(type);

        Thread.sleep(5000);
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
    public void message_willbe_displayed_as(String string) throws InterruptedException {
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.xpath("//h1[text()='Bill Payment Complete']"));
        Assert.assertEquals(element.isDisplayed(),true,"Payment of bill is successful Message" );
    }

    @Given("I want to do something")
    public void i_want_to_do_something(){
    }

    @When("I have a argumentts to send as")
    public void i_have_a_argument_to_send_as(String arg) {
        System.out.println("Arguments to be passed as: "+arg);
    }

    @When("I have a list of students to send as")
    public void i_have_a_list_of_students_to_send_as(List<String> list) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        System.out.println(list.toString());
    }

    @When("I have a list of students and their marks to send as")
    public void i_have_a_list_of_students_and_their_marks_to_send_as(Map<String, String> map ) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        System.out.println(map.toString());
    }

    @Then("Something should happen")
    public void something_should_happen() {

    }


    @Given("i am on the search page")
    public void i_am_on_the_search_page() {

    }
    @When("I search for the product: {string}")
    public void i_search_for_the_product_computer(String string) {
        System.out.println("Product searched: "+string);
    }
    @Then("Search result should be displayed to {string}")
    public void search_result_should_be_displayed_to(String string) {
        System.out.println("Product search success: "+string);
    }

}