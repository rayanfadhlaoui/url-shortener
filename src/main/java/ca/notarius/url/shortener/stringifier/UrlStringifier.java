package ca.notarius.url.shortener.stringifier;

import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import org.springframework.stereotype.Component;

@Component
public class UrlStringifier {

    private static final String DOMAIN_SEPARATOR = "/";

    public String getShortUrl(ShortenedUrlEntity shortenedUrlEntity) {
        return shortenedUrlEntity.getRoot().getValue() + DOMAIN_SEPARATOR + shortenedUrlEntity.getId();
    }
}
