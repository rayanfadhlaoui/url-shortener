package ca.notarius.url.shortener.adapter;

import ca.notarius.url.shortener.model.UrlEntity;

import java.math.BigInteger;
import java.util.Optional;

public interface UrlShortenerAdapter {

    UrlEntity save(UrlEntity shortenedUrlEntity);

    Optional<BigInteger> findByDomainAndPath(String domain, String path);
}
