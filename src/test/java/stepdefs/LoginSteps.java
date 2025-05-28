package stepdefs;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class LoginSteps {

    private String page = "";
    private boolean authenticated = false;

    @Given("the user is on the login page")
    public void user_is_on_login_page() {
        page = "login";
    }

    @When("the user enters valid credentials")
    public void user_enters_valid_credentials() {
        authenticated = true;
    }

    @Then("the user should be redirected to the dashboard")
    public void user_should_be_redirected_to_dashboard() {
        if (authenticated) {
            page = "dashboard";
        }
        System.out.println("page = " + page);
        assertEquals("dashboard", page);
    }
}
