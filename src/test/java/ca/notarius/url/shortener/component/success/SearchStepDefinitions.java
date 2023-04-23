package ca.notarius.url.shortener.component.success;

import ca.notarius.url.shortener.Application;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@CucumberContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SearchStepDefinitions {
    @Autowired
    private MockMvc mvc;
    private ResultActions currentAction;

    @When("we create a shortened url for {string}")
    public void userIsOnSearchPage(String url) throws Exception {
        MockHttpServletRequestBuilder postBuilder = post("/shortenedUrl")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(url);
        currentAction = mvc.perform(postBuilder);
    }

    @When("retrieving the original url back from {string}")
    public void retrievingTheOriginalUrlBackFrom(String shortenedUrl) throws Exception {
        MockHttpServletRequestBuilder postBuilder = get("/fullUrl")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(shortenedUrl);
        currentAction = mvc.perform(postBuilder);
    }

    @Then("it should be {string}")
    public void itShouldBe(String urlToAssert) throws Exception {
        currentAction
                .andExpect(status().isOk())
                .andExpect(content().string(urlToAssert));
    }


}