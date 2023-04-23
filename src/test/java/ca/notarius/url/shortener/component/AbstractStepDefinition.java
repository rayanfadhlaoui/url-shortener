package ca.notarius.url.shortener.component;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AbstractStepDefinition {

    private final MockMvc mvc;

    public AbstractStepDefinition(MockMvc mvc) {
        this.mvc = mvc;
    }

    protected ResultActions originalUrl(String shortenedUrl) throws Exception {
        String encodedUrl = URLEncoder.encode(shortenedUrl, StandardCharsets.UTF_8);

        MockHttpServletRequestBuilder postBuilder = get("/originalUrl/{shortenedUrl}", encodedUrl)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
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
