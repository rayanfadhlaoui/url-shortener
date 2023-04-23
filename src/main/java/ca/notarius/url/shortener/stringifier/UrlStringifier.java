package ca.notarius.url.shortener.stringifier;

import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class UrlStringifier {

    private static final String DOMAIN_SEPARATOR = "/";
    private static final String PROTOCOL_SEPARATOR = "://";

    public String getShortUrl(ShortenedUrlEntity shortenedUrlEntity) {
        return shortenedUrlEntity.getRoot().getRootValue() + DOMAIN_SEPARATOR + shortenedUrlEntity.getPathKey();
    }

    public String getDomain(URL url) {
        return url.getProtocol() + PROTOCOL_SEPARATOR + url.getHost();
    }
}
