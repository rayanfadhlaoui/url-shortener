package ca.notarius.url.shortener.adapter;

import ca.notarius.url.shortener.model.ShortenedUrlEntity;

import java.math.BigInteger;
import java.util.Optional;

public interface UrlShortenerAdapter {

    /**
     * Save a shortened url.
     *
     * @param shortenedUrlEntity The shortened url to save.
     * @return the save shortened url.
     */
    ShortenedUrlEntity save(ShortenedUrlEntity shortenedUrlEntity);

    /**
     * Find the key associated to the domain and path.
     *
     * @param domain The domain ex: 'https://www.norarius.com'
     * @param path   The path
     * @return an optional key associated to the domain and path.
     */
    Optional<BigInteger> findKeyByDomainAndPath(String domain, String path);

    /**
     * Find the path associated to the domain and key.
     *
     * @param domain The domain ex: 'https://www.norarius.com'
     * @param key    The key
     * @return an optional path associated to the domain and key.
     */
    Optional<String> findPathByDomainAndKey(String domain, BigInteger key);
}
