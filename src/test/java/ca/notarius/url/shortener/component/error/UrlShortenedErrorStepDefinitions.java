package ca.notarius.url.shortener.component.error;

import ca.notarius.url.shortener.Application;
import ca.notarius.url.shortener.component.AbstractStepDefinition;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@CucumberContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UrlShortenedErrorStepDefinitions extends AbstractStepDefinition {
    private ResultActions currentAction;

    public UrlShortenedErrorStepDefinitions(MockMvc mvc) {
        super(mvc);
    }

    @When("we create a shortened url for {string}")
    public void userIsOnSearchPage(String url) throws Exception {
        currentAction = postUrl(url);
    }

    @When("retrieving the original url back from {string}")
    public void retrievingTheOriginalUrlBackFrom(String shortenedUrl) throws Exception {
        currentAction = originalUrl(shortenedUrl);
    }

    @Then("it should be {string}")
    public void itShouldBe(String urlToAssert) throws Exception {
        currentAction
                .andExpect(status().isOk())
                .andExpect(content().string(urlToAssert));
    }


    @Then("it should be fail with not found")
    public void itShouldBeFailWithNotFound() throws Exception {
        currentAction.andExpect(status().isNotFound());
    }

    @Then("it should be fail with bad request")
    public void itShouldBeFailWithBadRequest() throws Exception {
        currentAction.andExpect(status().isBadRequest());
    }
}