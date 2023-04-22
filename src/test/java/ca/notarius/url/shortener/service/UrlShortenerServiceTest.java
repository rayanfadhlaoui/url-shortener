package ca.notarius.url.shortener.service;

import ca.notarius.url.shortener.adapter.UrlShortenerAdapter;
import ca.notarius.url.shortener.model.UrlEntity;
import ca.notarius.url.shortener.stringifier.UrlStringifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UrlShortenerServiceTest {
    private static final String EXPECTED_SHORTENED_URL = "https://www.notarius.com/1";
    private static final String ORIGINAL_URL = "https://www.notarius.com/career";

    @InjectMocks
    private UrlShortenerService urlShortenerService;
    @Mock
    private ShortenedUrlCreatorService shortenedUrlCreatorService;
    @Mock
    private UrlShortenerAdapter urlShortenerAdapter;
    @Mock
    private UrlStringifier urlStringifier;

    @Mock
    private UrlEntity urlEntity;
    @Mock
    private UrlEntity newUrlEntity;

    @Test
    void createAndSaveUrl() throws MalformedURLException {
        URL originalUrl = new URL(ORIGINAL_URL);
        given(shortenedUrlCreatorService.create(originalUrl)).willReturn(urlEntity);
        given(urlShortenerAdapter.save(urlEntity)).willReturn(newUrlEntity);
        given(urlStringifier.getShortUrl(newUrlEntity)).willReturn(EXPECTED_SHORTENED_URL);
        String actualUrl = urlShortenerService.createAndSaveUrl(originalUrl);
        assertThat(actualUrl).isEqualTo(EXPECTED_SHORTENED_URL);
    }
}