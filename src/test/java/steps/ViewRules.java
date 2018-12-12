package steps;

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
    private String rulesUrl = "http://localhost:8080/Regeln";
    private String matchHistoryUrl = "http://localhost:8080/SpielHistorie";
    public ViewRules(){
        //Driver muss heruntergeladen werden und der Pfad entsprechend geändert werden
        System.setProperty("webdriver.gecko.driver", "D:/Downloads/geckodriver-v0.23.0-win64/geckodriver.exe");
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get(url);
    }

    @Given("^I am on the \"([^\"]*)\"$")
    public void iAmOnThe(String arg0) throws Throwable {
        switch (arg0){
            case "homepage":
                Assert.assertEquals(url, webDriver.getCurrentUrl());
                System.out.println("You are on the correct page");
                break;
            case "rulespage":
                Assert.assertEquals(rulesUrl, webDriver.getCurrentUrl());
                System.out.println("You are on the correct page");
                break;
            case "matchhistory":
                Assert.assertEquals(matchHistoryUrl, webDriver.getCurrentUrl());
                System.out.println("You are on the correct page");
                break;
        }
    }

    @Then("^I am on the matchHistory page$")
    public void iAmOnTheMatchHistoryPage() throws Throwable {

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

    @When("^I click on element having id \"([^\"]*)\"$")
    public void i_click_on_element_having_id(String arg1) throws Throwable {
        webDriver.findElement(By.id(arg1)).click();
    }

    @Then("^element having id \"([^\"]*)\" should be present$")
    public void element_having_id_should_be_present(String arg1) throws Throwable {
       webDriver.findElement(By.id(arg1));
    }
}
