package ca.notarius.url.shortener.service;

import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import ca.notarius.url.shortener.model.UrlEntity;
import ca.notarius.url.shortener.service.key.UrlKeyProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.net.URL;

@Service
@AllArgsConstructor
public class ShortenedUrlCreatorService {

    private static final String PROTOCOL_SEPARATOR = "://";

    private final UrlKeyProviderService urlKeyProviderService;

    public ShortenedUrlEntity create(URL url) {
        String domain = url.getProtocol() + PROTOCOL_SEPARATOR + url.getHost();
        BigInteger nextKey = urlKeyProviderService.get(domain, url.getPath());
        return createShortenedUrlEntity(url, domain, nextKey);
    }

    private ShortenedUrlEntity createShortenedUrlEntity(URL url, String domain, BigInteger nextKey) {
        ShortenedUrlEntity shortenedUrlEntity = new ShortenedUrlEntity();
        shortenedUrlEntity.setOriginalPath(url.getPath());
        shortenedUrlEntity.setRoot(createUrlEntity(domain));
        shortenedUrlEntity.setId(nextKey);
        return shortenedUrlEntity;
    }

    private UrlEntity createUrlEntity(String domain) {
        var urlEntity = new UrlEntity();
        urlEntity.setValue(domain);
        return urlEntity;
    }
}
