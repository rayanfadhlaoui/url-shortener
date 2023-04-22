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

    public BigInteger get(String domain, String originalPath) {
        return urlShortenerAdapter.findByDomainAndPath(domain, originalPath)
                .orElseGet(() -> keyGeneratorService.getNextAndIncrement(domain));
    }
}
