package ca.notarius.url.shortener.service.key;

import ca.notarius.url.shortener.adapter.KeyGeneratorAdapter;
import ca.notarius.url.shortener.exceptions.KeysExceedsAcceptedSizeException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@AllArgsConstructor
public class KeyGeneratorService {

    private final static BigInteger LIMIT = BigInteger.valueOf(10000000000L);

    private KeyGeneratorAdapter keyGeneratorAdapter;

    /**
     * Get and increment the key of a given domain.
     *
     * @param domain the domain.
     * @return the current key linked to the domain. If none exist, return 1.
     */
    @Transactional
    public BigInteger getNextAndIncrement(String domain) {
        BigInteger currentKey = keyGeneratorAdapter.getCurrent(domain)
                .orElse(BigInteger.ONE);
        BigInteger incrementedKey = currentKey.add(BigInteger.ONE);
        if (incrementedKey.compareTo(LIMIT) >= 0) {
            throw new KeysExceedsAcceptedSizeException("key " + incrementedKey + " is too big");
        }
        keyGeneratorAdapter.save(domain, incrementedKey);
        return currentKey;
    }
}
