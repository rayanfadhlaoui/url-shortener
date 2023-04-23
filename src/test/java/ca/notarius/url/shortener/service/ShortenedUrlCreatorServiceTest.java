package ca.notarius.url.shortener.service;

import ca.notarius.url.shortener.service.key.UrlKeyProviderService;
import ca.notarius.url.shortener.stringifier.UrlStringifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ShortenedUrlCreatorServiceTest {

    private static final String ORIGINAL_URL = "https://www.notarius.com/career";
    private static final String DOMAIN = "https://www.notarius.com";
    private static final String CAREER_PATH = "/career";
    @InjectMocks
    private ShortenedUrlCreatorService shortenedUrlCreatorService;

    @Mock
    private UrlKeyProviderService urlKeyProviderService;
    @Mock
    private UrlStringifier urlStringifier;

    @Test
    void create() throws MalformedURLException {
        URL url = new URL(ORIGINAL_URL);
        BigInteger nextKey = BigInteger.valueOf(156);
        given(urlStringifier.getDomain(url)).willReturn(DOMAIN);
        given(urlKeyProviderService.get(DOMAIN, CAREER_PATH)).willReturn(nextKey);

        var shortenedUrlEntity = shortenedUrlCreatorService.create(url);

        var root = shortenedUrlEntity.getRoot();
        assertThat(root.getRootValue()).isEqualTo(DOMAIN);

        assertThat(shortenedUrlEntity.getOriginalPath()).isEqualTo(CAREER_PATH);
        assertThat(shortenedUrlEntity.getPathKey()).isEqualTo(nextKey);
    }
}