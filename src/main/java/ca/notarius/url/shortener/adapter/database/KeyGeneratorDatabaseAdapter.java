package ca.notarius.url.shortener.adapter.database;

import ca.notarius.url.shortener.adapter.KeyGeneratorAdapter;
import ca.notarius.url.shortener.adapter.database.jpa.KeyGeneratorDatabaseRepository;
import ca.notarius.url.shortener.model.KeyPerDomainEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Optional;

@Component
@AllArgsConstructor
public class KeyGeneratorDatabaseAdapter implements KeyGeneratorAdapter {

    private final KeyGeneratorDatabaseRepository keyGeneratorDatabaseRepository;

    @Override
    public Optional<BigInteger> getCurrent(String domain) {
        return keyGeneratorDatabaseRepository.getCurrent(domain);
    }

    @Override
    public BigInteger save(String domain, BigInteger key) {
        var keyPerDomainEntity = new KeyPerDomainEntity();
        keyPerDomainEntity.setDomain(domain);
        keyPerDomainEntity.setId(key);
        var savedKeyPerDomainEntity = keyGeneratorDatabaseRepository.save(keyPerDomainEntity);
        return savedKeyPerDomainEntity.getId();
    }
}
