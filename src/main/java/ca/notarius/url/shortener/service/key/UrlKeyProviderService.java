package ca.notarius.url.shortener.service.key;

import ca.notarius.url.shortener.adapter.UrlShortenerAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@AllArgsConstructor
public class UrlKeyProviderService {

    private final UrlShortenerAdapter urlShortenerAdapter;

    private final KeyGeneratorService keyGeneratorService;

    /**
     * Get the existing key mapped to a given domain and originalPath or get the current one linked to the domain.
     *
     * @param domain       The domain.
     * @param originalPath the origial path.
     * @return a key associated to a domain and originalPath.
     */
    public BigInteger get(String domain, String originalPath) {
        return urlShortenerAdapter.findKeyByDomainAndPath(domain, originalPath)
                .orElseGet(() -> keyGeneratorService.getNextAndIncrement(domain));
    }
}
