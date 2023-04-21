package ca.nautarius.url.shortener.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class ShortenedUrlEntity {

    private BigInteger id;
    private String originalPath;

}
