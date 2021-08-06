package Stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Stepdefs {

    WebDriver driver;
    String url = "https://parabank.parasoft.com/parabank/index.html";
    String username = "John";
    String password = "demo";


    @Given("user opened the browser")
    public void user_opened_the_browser() {

        driver = new ChromeDriver();
    }

    @Given("user navigated to the application url")
    public void user_navigated_to_the_application_url() {
        driver.get(url);
    }

    @When("user enter the username as {string} and password as {string} and click the login button")
    public void user_enter_the_username_as_and_password_as_and_click_the_login_button(String username, String password)
    {
        driver.findElement(By.name("username")).sendKeys("john");
       driver.findElement(By.name("password")).sendKeys("demo");
       driver.findElement(By.xpath("//input[@value='Log In']")).click();
    }

    @Then("user is able to login in the application")
    public void user_is_able_to_login_in_the_application() {
        Assert.assertEquals("ParaBank | Accounts Overview", driver.getTitle());
        boolean actualTableDisplayed = driver.findElement(By.id("accountTable")).isDisplayed();
        Assert.assertEquals(true,actualTableDisplayed);    }

}