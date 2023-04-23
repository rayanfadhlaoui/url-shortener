package ca.notarius.url.shortener.stringifier;

import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class UrlStringifier {

    private static final String DOMAIN_SEPARATOR = "/";
    private static final String PROTOCOL_SEPARATOR = "://";

    /**
     * Get the short version of the original url associated with {@shortenedUrlEntity}.
     *
     * @param shortenedUrlEntity The entity that hold information on both original and shorter url.
     * @return The representation of the short url.
     */
    public String getShortUrl(ShortenedUrlEntity shortenedUrlEntity) {
        return shortenedUrlEntity.getDomain().getDomainValue() + DOMAIN_SEPARATOR + shortenedUrlEntity.getPathKey();
    }

    /**
     * Get the domain associated to an url.
     *
     * @param url The url.
     * @return the domain.
     */
    public String getDomain(URL url) {
        return url.getProtocol() + PROTOCOL_SEPARATOR + url.getHost();
    }
}
