package ca.notarius.url.shortener.service;

import ca.notarius.url.shortener.adapter.UrlShortenerAdapter;
import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import ca.notarius.url.shortener.stringifier.UrlStringifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ShortenerUrlServiceTest {
    private static final String DOMAIN = "https://www.notarius.com";
    private static final String KEY = "5415561";
    private static final String EXPECTED_SHORTENED_URL = "https://www.notarius.com/1";
    private static final String ORIGINAL_URL = "https://www.notarius.com/career";

    @InjectMocks
    private ShortenerUrlService shortenerUrlService;
    @Mock
    private ShortenedUrlCreatorService shortenedUrlCreatorService;
    @Mock
    private UrlShortenerAdapter urlShortenerAdapter;
    @Mock
    private UrlStringifier urlStringifier;

    @Mock
    private ShortenedUrlEntity shortenedUrlEntity;
    @Mock
    private ShortenedUrlEntity newShortenedUrlEntity;

    @Test
    void createAndSaveUrl() throws MalformedURLException {
        URL originalUrl = new URL(ORIGINAL_URL);
        given(shortenedUrlCreatorService.create(originalUrl)).willReturn(shortenedUrlEntity);
        given(urlShortenerAdapter.save(shortenedUrlEntity)).willReturn(newShortenedUrlEntity);
        given(urlStringifier.getShortUrl(newShortenedUrlEntity)).willReturn(EXPECTED_SHORTENED_URL);

        String actualUrl = shortenerUrlService.createAndSaveUrl(originalUrl);

        assertThat(actualUrl).isEqualTo(EXPECTED_SHORTENED_URL);
    }

    @Test
    void getFullUrl() {
        given(urlShortenerAdapter.findPathByDomainAndKey(DOMAIN, BigInteger.valueOf(5415561L))).willReturn(Optional.of("/career"));

        Optional<String> actualUrl = shortenerUrlService.getFullUrl(DOMAIN, KEY);

        assertThat(actualUrl.isPresent()).isTrue();
        assertThat(actualUrl.get()).isEqualTo(ORIGINAL_URL);
    }

    @Test
    void getFullUrl_NotPresent() {
        given(urlShortenerAdapter.findPathByDomainAndKey(DOMAIN, BigInteger.valueOf(5415561L))).willReturn(Optional.empty());

        Optional<String> actualUrl = shortenerUrlService.getFullUrl(DOMAIN, KEY);

        assertThat(actualUrl.isEmpty()).isTrue();
    }
}