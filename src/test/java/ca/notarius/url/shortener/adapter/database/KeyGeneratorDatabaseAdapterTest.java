package ca.notarius.url.shortener.adapter.database;

import ca.notarius.url.shortener.adapter.database.jpa.KeyGeneratorDatabaseRepository;
import ca.notarius.url.shortener.model.KeyPerDomainEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class KeyGeneratorDatabaseAdapterTest {
    private static final String DOMAIN = "https://www.notarius.com";
    private static final BigInteger KEY = BigInteger.valueOf(123);
    @InjectMocks
    private KeyGeneratorDatabaseAdapter keyGeneratorDatabaseAdapter;

    @Mock
    private KeyGeneratorDatabaseRepository keyGeneratorDatabaseRepository;

    @Mock
    private KeyPerDomainEntity newKeyPerDomainEntity;

    @Test
    void save() {
        given(keyGeneratorDatabaseRepository.save(keyPerDomainEntity())).willReturn(newKeyPerDomainEntity);
        given(newKeyPerDomainEntity.getId()).willReturn(KEY);
        var savedKeyPerDomainEntity = keyGeneratorDatabaseAdapter.save(DOMAIN, KEY);

        assertThat(savedKeyPerDomainEntity).isEqualTo(KEY);
    }

    @Test
    void getCurrent() {
        given(keyGeneratorDatabaseRepository.getCurrent(DOMAIN)).willReturn(Optional.of(KEY));
        Optional<BigInteger> optionalKey = keyGeneratorDatabaseAdapter.getCurrent(DOMAIN);

        assertThat(optionalKey.isPresent()).isTrue();
        assertThat(optionalKey.get()).isEqualTo(KEY);
    }

    @Test
    void getCurrent_NotPresent() {
        given(keyGeneratorDatabaseRepository.getCurrent(DOMAIN)).willReturn(Optional.empty());
        Optional<BigInteger> optionalKey = keyGeneratorDatabaseAdapter.getCurrent(DOMAIN);

        assertThat(optionalKey.isEmpty()).isTrue();
    }

    private KeyPerDomainEntity keyPerDomainEntity() {
        return argThat(keyPerDomainEntity -> {
            assertThat(keyPerDomainEntity.getDomain()).isEqualTo(DOMAIN);
            assertThat(keyPerDomainEntity.getId()).isEqualTo(KEY);
            return true;
        });
    }
}