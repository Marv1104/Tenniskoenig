package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

public class CreateMatch {

    @Given("^I am logged in$")
    public void i_am_logged_in() throws Throwable {
        System.out.println("Du bist eingeloggt");
    }

    @Given("^I am on the enterMatch page$")
    public void i_am_on_the_enterMatch_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I select \"([^\"]*)\" option by text from dropdown having id \"([^\"]*)\"$")
    public void i_select_option_by_text_from_dropdown_having_id(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I uncheck checkbox having id \"([^\"]*)\"$")
    public void i_uncheck_checkbox_having_id(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
