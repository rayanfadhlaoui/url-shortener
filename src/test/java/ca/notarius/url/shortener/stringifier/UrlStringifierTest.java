package ca.notarius.url.shortener.stringifier;

import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import ca.notarius.url.shortener.model.UrlEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.assertj.core.api.Java6Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UrlStringifierTest {

    private static final String ROOT = "https://www.notarius.com";
    @InjectMocks
    private UrlStringifier urlStringifier;

    @Test
    void stringify() {
        var urlEntity = createUrlEntity();
        String shortUrl = urlStringifier.getShortUrl(urlEntity);
        assertThat(shortUrl).isEqualTo("https://www.notarius.com/9999999999");
    }

    private static UrlEntity createUrlEntity() {
        var urlEntity = new UrlEntity();
        var shortenedUrlEntity = new ShortenedUrlEntity();
        shortenedUrlEntity.setId(BigInteger.valueOf(9999999999L));
        urlEntity.setRoot(ROOT);
        urlEntity.setShortenedUrl(shortenedUrlEntity);
        return urlEntity;
    }

}