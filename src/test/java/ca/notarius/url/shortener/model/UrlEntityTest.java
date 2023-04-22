package ca.notarius.url.shortener.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UrlEntityTest {

    @Test
    void urlEntity() {
        var urlEntity = new UrlEntity();
        long expectedId = 10000000000L;
        urlEntity.setId(expectedId);
        String expectedUrlValue = "https://www.domain.com";
        urlEntity.setRootValue(expectedUrlValue);
        assertThat(urlEntity.getId()).isEqualTo(expectedId);
        assertThat(urlEntity.getRootValue()).isEqualTo(expectedUrlValue);
    }
}