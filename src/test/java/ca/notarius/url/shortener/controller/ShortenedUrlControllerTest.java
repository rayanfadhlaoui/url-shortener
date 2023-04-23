package ca.notarius.url.shortener.controller;

import ca.notarius.url.shortener.service.ShortenerUrlService;
import ca.notarius.url.shortener.stringifier.UrlStringifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ShortenedUrlControllerTest {

    private static final String VALID_URL = "https://www.notarius.com/careers/softwareEngineer";
    private static final String INVALID_URL = "httpswww.notarius.com/careers/softwareEngineer";
    private static final String URL_WITH_MISSING_PATH = "https://www.notarius.com/";
    private static final String SHORTENED_URL = "https://www.notarius.com/1";
    private static final String ENCODE_URL = URLEncoder.encode(SHORTENED_URL, StandardCharsets.UTF_8);
    @InjectMocks
    private ShortenedUrlController shortenedUrlController;

    @Mock
    private ShortenerUrlService shortenerUrlService;
    @Mock
    private UrlStringifier urlStringifier;

    @Test
    void shortenedUrl() throws MalformedURLException {
        given(shortenerUrlService.createAndSaveUrl(new URL(VALID_URL))).willReturn(SHORTENED_URL);
        ResponseEntity<String> responseEntity = shortenedUrlController.shortenedUrl(VALID_URL);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(SHORTENED_URL);
    }

    @Test
    void shortenedUrl_MalformedURL() {
        ResponseEntity<String> responseEntity = shortenedUrlController.shortenedUrl(INVALID_URL);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isEqualTo("Url was malformed or path was missing");
    }

    @Test
    void shortenedUrl_missingPath() {
        ResponseEntity<String> responseEntity = shortenedUrlController.shortenedUrl(URL_WITH_MISSING_PATH);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isEqualTo("Url was malformed or path was missing");
    }

    @Test
    void fullUrl() throws MalformedURLException {
        given(shortenerUrlService.getFullUrl("https://www.notarius.com", "1")).willReturn(Optional.of(VALID_URL));
        mockGetDomain();
        given(shortenerUrlService.getFullUrl("https://www.notarius.com", "1")).willReturn(Optional.of(VALID_URL));
        ResponseEntity<String> responseEntity = shortenedUrlController.fullUrl(SHORTENED_URL);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(VALID_URL);
    }

    @Test
    void fullUrl_WithEncodedPath() throws MalformedURLException {
        given(shortenerUrlService.getFullUrl("https://www.notarius.com", "1")).willReturn(Optional.of(VALID_URL));
        mockGetDomain();
        given(shortenerUrlService.getFullUrl("https://www.notarius.com", "1")).willReturn(Optional.of(VALID_URL));
        ResponseEntity<String> responseEntity = shortenedUrlController.fullUrl(ENCODE_URL);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(VALID_URL);
    }

    @Test
    void fullUrl_NotFound() throws MalformedURLException {
        given(shortenerUrlService.getFullUrl("https://www.notarius.com", "1")).willReturn(Optional.empty());
        mockGetDomain();
        ResponseEntity<String> responseEntity = shortenedUrlController.fullUrl(SHORTENED_URL);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    void fullUrl_MalformedURL() {
        ResponseEntity<String> responseEntity = shortenedUrlController.fullUrl(INVALID_URL);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isEqualTo("no protocol: httpswww.notarius.com/careers/softwareEngineer");
    }

    @Test
    void fullUrl_IsNotAShortenedUrl() {
        ResponseEntity<String> responseEntity = shortenedUrlController.fullUrl(VALID_URL);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isEqualTo("Url was not shortened by this service.");
    }

    private void mockGetDomain() throws MalformedURLException {
        URL url = new URL(SHORTENED_URL);
        given(urlStringifier.getDomain(url)).willReturn("https://www.notarius.com");
    }
}