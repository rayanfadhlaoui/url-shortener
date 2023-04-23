package ca.notarius.url.shortener.component;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AbstractStepDefinition {

    private final MockMvc mvc;

    public AbstractStepDefinition(MockMvc mvc) {
        this.mvc = mvc;
    }

    protected ResultActions getFullUrl(String shortenedUrl) throws Exception {
        MockHttpServletRequestBuilder postBuilder = get("/fullUrl")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(shortenedUrl);
        return mvc.perform(postBuilder);
    }

    protected ResultActions postUrl(String url) throws Exception {
        MockHttpServletRequestBuilder postBuilder = post("/shortenedUrl")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(url);
        return mvc.perform(postBuilder);

    }

}
