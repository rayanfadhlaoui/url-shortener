package ca.notarius.url.shortener.service;

import ca.notarius.url.shortener.adapter.UrlShortenerAdapter;
import ca.notarius.url.shortener.stringifier.UrlStringifier;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.net.URL;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShortenerUrlService {
    private final ShortenedUrlCreatorService shortenedUrlCreatorService;
    private final UrlShortenerAdapter urlShortenerAdapter;
    private final UrlStringifier urlStringifier;

    /**
     * Create and shorter url and saves its previous path and domain.
     *
     * @param originalUrl The original url.
     * @return the shorterUrl.
     */
    public String createAndSaveUrl(URL originalUrl) {
        var urlEntity = shortenedUrlCreatorService.create(originalUrl);
        var newUrlEntity = urlShortenerAdapter.save(urlEntity);
        return urlStringifier.getShortUrl(newUrlEntity);
    }

    /**
     * Get the original url from a given domain and key if it exists.
     *
     * @param domain The domain
     * @param key    The key
     * @return an optional representation of the original url.
     */
    public Optional<String> getOriginalUrl(String domain, String key) {
        BigInteger keyAsBigInt = new BigInteger(key);
        return urlShortenerAdapter.findPathByDomainAndKey(domain, keyAsBigInt)
                .map(path -> domain + path);
    }
}
