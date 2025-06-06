package stepdefs;

import com.learn.exchangeservice.entity.UserEntity;
import com.learn.exchangeservice.repo.UserRepo;
import com.learn.exchangeservice.test.Test;
import com.learn.test.Test.Tets;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.*;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class LoginSteps {

    @Autowired
    private UserRepo userRepo ;
    @Autowired
    private DataSource dataSource;

    private String page = "";
    private boolean authenticated = false;

    public LoginSteps() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    public void runSqlFile(String sqlFilePath) throws Exception {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(getClass().getClassLoader().getResourceAsStream(sqlFilePath)))) {

            String sql = reader.lines().collect(Collectors.joining("\n"));

            // Assumes file has semicolon-separated SQL statements
            for (String sqlStatement : sql.split(";")) {
                if (!sqlStatement.trim().isEmpty()) {
                    stmt.execute(sqlStatement.trim());
                }
            }
        }
    }

    @BeforeStep
    public void setUp() throws Exception {
        runSqlFile("sql/user.sql");

    }



    @Given("the user is on the login page")
    public void user_is_on_ogin_page() {
        page = "login";
        Tets tets = new Tets();
        tets.tets();
        Test test = new Test();
//        Mockito.when(userRepo.findAll()).thenReturn(List.of(new UserEntity()));
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
