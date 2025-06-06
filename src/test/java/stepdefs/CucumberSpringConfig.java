package stepdefs;

import config.JpaTestConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@CucumberContextConfiguration
@ContextConfiguration(classes = JpaTestConfig.class)
public class CucumberSpringConfig extends AbstractTransactionalJUnit4SpringContextTests {
}