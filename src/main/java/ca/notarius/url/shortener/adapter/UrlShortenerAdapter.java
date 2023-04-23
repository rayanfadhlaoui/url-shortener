package ca.notarius.url.shortener.adapter;

import ca.notarius.url.shortener.model.ShortenedUrlEntity;

import java.math.BigInteger;
import java.util.Optional;

public interface UrlShortenerAdapter {

    ShortenedUrlEntity save(ShortenedUrlEntity shortenedUrlEntity);

    Optional<BigInteger> findByDomainAndPath(String domain, String path);

    Optional<String> findPathByDomainAndKey(String domain, BigInteger key);
}
