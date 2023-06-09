package ca.notarius.url.shortener.component.success;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/success",
        glue = "ca.notarius.url.shortener.component.success",
        plugin = {"pretty", "html:target/cucumber-reports"})
public class RunCucumberSuccessRunCukesTest {

}