package ca.notarius.url.shortener.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlEntity {

    private String root;
    private ShortenedUrlEntity shortenedUrl;
}
