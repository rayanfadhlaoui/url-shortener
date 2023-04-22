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

    public UrlEntity create(URL url) {
        String domain = url.getProtocol() + PROTOCOL_SEPARATOR + url.getHost();
        BigInteger nextKey = urlKeyProviderService.get(domain, url.getPath());
        return createUrlEntity(url, domain, nextKey);
    }

    private UrlEntity createUrlEntity(URL url, String domain, BigInteger nextKey) {
        var urlEntity = new UrlEntity();
        var shortenedUrlEntity = createShortenedUrlEntity(url, nextKey);
        urlEntity.setRoot(domain);
        urlEntity.setShortenedUrl(shortenedUrlEntity);
        return urlEntity;
    }

    private ShortenedUrlEntity createShortenedUrlEntity(URL url, BigInteger nextKey) {
        ShortenedUrlEntity shortenedUrlEntity = new ShortenedUrlEntity();
        shortenedUrlEntity.setOriginalPath(url.getPath());
        shortenedUrlEntity.setId(nextKey);
        return shortenedUrlEntity;
    }
}
