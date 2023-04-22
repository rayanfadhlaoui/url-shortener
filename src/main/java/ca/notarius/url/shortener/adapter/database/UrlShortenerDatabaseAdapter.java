package ca.notarius.url.shortener.adapter.database;

import ca.notarius.url.shortener.adapter.UrlShortenerAdapter;
import ca.notarius.url.shortener.model.UrlEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public class UrlShortenerDatabaseAdapter implements UrlShortenerAdapter {

    @Override
    public UrlEntity save(UrlEntity shortenedUrlEntity) {
        return null;
    }

    @Override
    public Optional<BigInteger> findByDomainAndPath(String domain, String path) {
        return Optional.empty();
    }
}