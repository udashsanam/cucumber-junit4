package stepdefs;

import com.learn.exchangeservice.entity.UserEntity;
import com.learn.exchangeservice.repo.UserRepo;
import com.learn.exchangeservice.test.Test;
import com.learn.test.Test.Tets;
import io.cucumber.java.en.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;


public class LoginSteps {

    private String page = "";
    private boolean authenticated = false;


    private UserRepo userRepo = Mockito.mock(UserRepo.class);

    @Given("the user is on the login page")
    public void user_is_on_login_page() {
        page = "login";
        Tets tets = new Tets();
        tets.tets();
        Test test = new Test();
        Mockito.when(userRepo.findAll()).thenReturn(List.of(new UserEntity()));
        test.print();
        List<UserEntity> userEntities = userRepo.findAll();
        System.out.println(userEntities.get(0).getId());

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
