package ca.nautarius.url.shortener.service.key;

import ca.nautarius.url.shortener.adapter.UrlShortenerAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@AllArgsConstructor
public class UrlKeyProviderService {

    private final UrlShortenerAdapter urlShortenerAdapter;

    private final KeyGeneratorService keyGeneratorService;

    public BigInteger get(String originalPath) {
        return urlShortenerAdapter.findByPath(originalPath)
                .orElseGet(keyGeneratorService::getNextAndIncrement);
    }
}
