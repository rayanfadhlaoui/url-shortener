package ca.notarius.url.shortener.component.error;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/error",
        glue = "ca.notarius.url.shortener.component.error",
        plugin = {"pretty", "html:target/cucumber-reports"})
public class RunCucumberErrorRunCukesTest {

}