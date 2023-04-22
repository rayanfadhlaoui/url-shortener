package ca.notarius.url.shortener.adapter.database;

import ca.notarius.url.shortener.adapter.UrlShortenerAdapter;
import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UrlShortenerDatabaseAdapter implements UrlShortenerAdapter {

    private final UrlShortenerDatabaseRepository urlShortenerDatabaseRepository;

    @Override
    public ShortenedUrlEntity save(ShortenedUrlEntity shortenedUrlEntity) {
        return urlShortenerDatabaseRepository.save(shortenedUrlEntity);
    }

    @Override
    public Optional<BigInteger> findByDomainAndPath(String domain, String path) {
        return urlShortenerDatabaseRepository.findByDomainAndPath(domain, path);
    }
}
