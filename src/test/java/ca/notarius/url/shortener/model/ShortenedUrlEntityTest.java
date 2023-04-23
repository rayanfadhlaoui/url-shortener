package ca.notarius.url.shortener.model;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

class ShortenedUrlEntityTest {

    @Test
    void entityGetter() {
        String originalPath = "anything";
        var shortenedUrlEntity = new ShortenedUrlEntity();
        shortenedUrlEntity.setOriginalPath(originalPath);
        shortenedUrlEntity.setId(BigInteger.ONE);
        shortenedUrlEntity.setPathKey(BigInteger.TWO);

        assertThat(shortenedUrlEntity.getOriginalPath()).isEqualTo(originalPath);
        assertThat(shortenedUrlEntity.getId()).isEqualTo(BigInteger.ONE);
        assertThat(shortenedUrlEntity.getPathKey()).isEqualTo(BigInteger.TWO);
    }
}