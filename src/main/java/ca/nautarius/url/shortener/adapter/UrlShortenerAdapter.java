package ca.nautarius.url.shortener.adapter;

import ca.nautarius.url.shortener.model.ShortenedUrlEntity;

public interface UrlShortenerAdapter {

    ShortenedUrlEntity save(ShortenedUrlEntity shortenedUrlEntity);

}
