package ca.nautarius.url.shortener.service;

import ca.nautarius.url.shortener.adapter.UrlShortenerAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UrlShortenerServiceTest {

    private static final String DOMAIN = "https://www.notarius.com";
    private static final String INDUSTRY_SHORT_KEY = "/fr/indust";
    private static final String INDUSTRY_URL = "/fr/industries/technologues";
    @InjectMocks
    private UrlShortenerService urlShortenerService;

    @Mock
    private UrlShortenerAdapter urlShortenerAdapter;

    @Test
    void shortenerUrl_UrlIsLongerThan10() throws MalformedURLException {
        var url = new URL(DOMAIN + INDUSTRY_URL);
        given(urlShortenerAdapter.getCount(INDUSTRY_SHORT_KEY)).willReturn(0);
        String shortenerUrl = urlShortenerService.shortenerUrl(url);
        assertThat(shortenerUrl).isEqualTo(DOMAIN + INDUSTRY_SHORT_KEY);
    }

    @Test
    void shortenerUrl_UrlIsLessThan10() throws MalformedURLException {
        String key = "/fr";
        var url = new URL(DOMAIN + key);
        given(urlShortenerAdapter.getCount(key)).willReturn(0);
        String shortenerUrl = urlShortenerService.shortenerUrl(url);
        assertThat(shortenerUrl).isEqualTo(DOMAIN + key);
    }

    @Test
    void shortenerUrl_TwoUrlGiveTheSameOutput() throws MalformedURLException {
        var url = new URL(DOMAIN + INDUSTRY_URL);
        given(urlShortenerAdapter.getCount(INDUSTRY_SHORT_KEY)).willReturn(1);
        String shortenerUrl = urlShortenerService.shortenerUrl(url);
        assertThat(shortenerUrl).isEqualTo(DOMAIN + "/fr/indus2");
    }

    @Test
    void shortenerUrl_1000UrlGiveTheSameOutput() throws MalformedURLException {
        var url = new URL(DOMAIN + INDUSTRY_URL);
        given(urlShortenerAdapter.getCount(INDUSTRY_SHORT_KEY)).willReturn(1000);
        String shortenerUrl = urlShortenerService.shortenerUrl(url);
        assertThat(shortenerUrl).isEqualTo(DOMAIN + "/fr/in1001");
    }

}