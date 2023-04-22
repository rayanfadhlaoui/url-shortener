package ca.notarius.url.shortener.adapter.database;

import ca.notarius.url.shortener.adapter.KeyGeneratorAdapter;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public class KeyGeneratorDatabaseAdapter implements KeyGeneratorAdapter {
    @Override
    public Optional<BigInteger> getCurrent(String domain) {
        return Optional.empty();
    }

    @Override
    public BigInteger save(String domain, BigInteger key) {
        return null;
    }
}
