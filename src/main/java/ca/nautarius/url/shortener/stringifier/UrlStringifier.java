package ca.nautarius.url.shortener.stringifier;

import ca.nautarius.url.shortener.model.UrlEntity;
import org.springframework.stereotype.Component;

@Component
public class UrlStringifier {

    private static final String DOMAIN_SEPARATOR = "/";

    public String getShortUrl(UrlEntity urlEntity) {
        return urlEntity.getRoot() + DOMAIN_SEPARATOR + urlEntity.getShortenedUrl().getId();
    }
}
