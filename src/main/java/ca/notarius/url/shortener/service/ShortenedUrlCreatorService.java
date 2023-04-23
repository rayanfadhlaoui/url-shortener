package ca.notarius.url.shortener.service;

import ca.notarius.url.shortener.model.DomainEntity;
import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import ca.notarius.url.shortener.service.key.UrlKeyProviderService;
import ca.notarius.url.shortener.stringifier.UrlStringifier;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.net.URL;

@Service
@AllArgsConstructor
public class ShortenedUrlCreatorService {

    private final UrlKeyProviderService urlKeyProviderService;
    private final UrlStringifier urlStringifier;

    /**
     * Creates a shorter url represented as {@ShortenedUrlEntity} and increment the key linked to the given domain.
     *
     * @param originalUrl The origial Url.
     * @return an entity representing the shorter url.
     */
    public ShortenedUrlEntity create(URL originalUrl) {
        String domain = urlStringifier.getDomain(originalUrl);
        BigInteger nextKey = urlKeyProviderService.get(domain, originalUrl.getPath());
        return createShortenedUrlEntity(originalUrl, domain, nextKey);
    }

    private ShortenedUrlEntity createShortenedUrlEntity(URL url, String domain, BigInteger nextKey) {
        var shortenedUrlEntity = new ShortenedUrlEntity();
        shortenedUrlEntity.setOriginalPath(url.getPath());
        shortenedUrlEntity.setDomain(createDomainEntity(domain));
        shortenedUrlEntity.setPathKey(nextKey);
        return shortenedUrlEntity;
    }

    private DomainEntity createDomainEntity(String domain) {
        var domainEntity = new DomainEntity();
        domainEntity.setDomainValue(domain);
        return domainEntity;
    }
}
