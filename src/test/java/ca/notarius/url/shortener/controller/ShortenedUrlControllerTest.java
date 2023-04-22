package ca.notarius.url.shortener.controller;

import ca.notarius.url.shortener.service.ShortenerUrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ShortenedUrlControllerTest {

    private static final String VALID_URL = "https://www.notarius.com/careers/softwareEngineer";
    private static final String INVALID_URL = "httpswww.notarius.com/careers/softwareEngineer";
    public static final String EXPECTED_URL = "https://www.notarius.com/1";
    @InjectMocks
    private ShortenedUrlController shortenedUrlController;

    @Mock
    private ShortenerUrlService shortenerUrlService;

    @Test
    void shortenedUrl() throws MalformedURLException {
        given(shortenerUrlService.createAndSaveUrl(new URL(VALID_URL))).willReturn(EXPECTED_URL);
        ResponseEntity<String> responseEntity = shortenedUrlController.shortenedUrl(VALID_URL);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(EXPECTED_URL);
    }

    @Test
    void shortenedUrl_MalformedURLException() {
        ResponseEntity<String> responseEntity = shortenedUrlController.shortenedUrl(INVALID_URL);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isEqualTo("Url was malformed");
    }
}