package ca.notarius.url.shortener.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DomainEntityTest {

    @Test
    void domainEntity() {
        var domainEntity = new DomainEntity();
        long expectedId = 10000000000L;
        domainEntity.setId(expectedId);
        String expectedUrlValue = "https://www.domain.com";
        domainEntity.setDomainValue(expectedUrlValue);
        assertThat(domainEntity.getId()).isEqualTo(expectedId);
        assertThat(domainEntity.getDomainValue()).isEqualTo(expectedUrlValue);
    }
}