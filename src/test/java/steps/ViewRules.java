package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ViewRules {
    private WebDriver webDriver;
    private String url = "http://localhost:8080/";
    private String targetUrl = "http://localhost:8080/Regeln";
    public ViewRules(){
        System.setProperty("webdriver.gecko.driver", "D:/Downloads/geckodriver-v0.23.0-win64/geckodriver.exe");
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Given("^I am on the hompepage$")
    public void i_am_on_the_hompepage() throws Throwable {
        webDriver.get(url);
    }

    @When("^I click on element having id \"([^\"]*)\"$")
    public void i_click_on_element_having_id(String arg1) throws Throwable {
        webDriver.findElement(By.id(arg1)).click();
    }

    @Then("^I am on the rules page$")
    public void i_am_on_the_rules_page() throws Throwable {
        ;try{
            Assert.assertEquals(url, webDriver.getCurrentUrl());
            System.out.println("Navigated to correct webpage");
        }
        catch(Throwable pageNavigationError){
            System.out.println("Didn't navigate to correct webpage");
        }
    }

    @Then("^element having id \"([^\"]*)\" should be present$")
    public void element_having_id_should_be_present(String arg1) throws Throwable {
       webDriver.findElement(By.id(arg1));
    }
}
