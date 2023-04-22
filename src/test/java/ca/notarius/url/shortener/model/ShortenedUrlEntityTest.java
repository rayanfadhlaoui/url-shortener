package ca.notarius.url.shortener.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShortenedUrlEntityTest {

    @Test
    void entityGetter() {
        String originalPath = "anything";
        var shortenedUrlEntity = new ShortenedUrlEntity();
        shortenedUrlEntity.setOriginalPath(originalPath);
        assertThat(shortenedUrlEntity.getOriginalPath()).isEqualTo(originalPath);
    }
}