package ca.nautarius.url.shortener.adapter.database;

import ca.nautarius.url.shortener.adapter.UrlShortenerAdapter;
import ca.nautarius.url.shortener.model.ShortenedUrlEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public class UrlShortenerDatabaseAdapter implements UrlShortenerAdapter {
    @Override
    public ShortenedUrlEntity save(ShortenedUrlEntity shortenedUrlEntity) {
        return null;
    }

    @Override
    public Optional<BigInteger> findByPath(String path) {
        return Optional.empty();
    }
}
