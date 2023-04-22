package ca.notarius.url.shortener.adapter.database.jpa;

import ca.notarius.url.shortener.model.KeyPerDomainEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class KeyGeneratorDatabaseRepositoryJpaTest {

    private static final String DOMAIN = "https://www.notarius.com";

    @Autowired
    private KeyGeneratorDatabaseRepository keyGeneratorDatabaseRepository;


    @Test
    void getCurrent() {
        var keyPerDomainEntity = new KeyPerDomainEntity();
        BigInteger expectedKey = BigInteger.ONE;
        keyPerDomainEntity.setId(expectedKey);
        keyPerDomainEntity.setDomain(DOMAIN);
        keyGeneratorDatabaseRepository.save(keyPerDomainEntity);

        Optional<BigInteger> currentKey = keyGeneratorDatabaseRepository.getCurrent(DOMAIN);

        assertThat(currentKey.isPresent()).isTrue();
        assertThat(currentKey.get()).isEqualTo(expectedKey);
    }

    @Test
    void getCurrent_NoMatch() {
        Optional<BigInteger> currentKey = keyGeneratorDatabaseRepository.getCurrent(DOMAIN);

        assertThat(currentKey.isEmpty()).isTrue();
    }
}