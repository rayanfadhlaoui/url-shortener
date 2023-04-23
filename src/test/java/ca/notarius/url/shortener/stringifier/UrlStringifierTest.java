package ca.notarius.url.shortener.stringifier;

import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import ca.notarius.url.shortener.model.DomainEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Java6Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UrlStringifierTest {

    private static final String ROOT = "https://www.notarius.com";
    @InjectMocks
    private UrlStringifier urlStringifier;

    @Test
    void getShortUrl() {
        var urlEntity = createShortenedUrlEntity();
        String shortUrl = urlStringifier.getShortUrl(urlEntity);
        assertThat(shortUrl).isEqualTo("https://www.notarius.com/9999999999");
    }

    @Test
    void getDomain() throws MalformedURLException {
        URL url = new URL("https://www.notarius.com/9999999999");
        String domain = urlStringifier.getDomain(url);
        assertThat(domain).isEqualTo("https://www.notarius.com");
    }

    private ShortenedUrlEntity createShortenedUrlEntity() {
        var shortenedUrlEntity = new ShortenedUrlEntity();
        var domainEntity = new DomainEntity();
        domainEntity.setDomainValue(ROOT);
        shortenedUrlEntity.setPathKey(BigInteger.valueOf(9999999999L));
        shortenedUrlEntity.setDomain(domainEntity);
        return shortenedUrlEntity;
    }

}