package ca.nautarius.url.shortener.adapter;

import ca.nautarius.url.shortener.model.ShortenedUrlEntity;

import java.math.BigInteger;
import java.util.Optional;

public interface UrlShortenerAdapter {

    ShortenedUrlEntity save(ShortenedUrlEntity shortenedUrlEntity);

    Optional<BigInteger> findByPath(String path);
}
