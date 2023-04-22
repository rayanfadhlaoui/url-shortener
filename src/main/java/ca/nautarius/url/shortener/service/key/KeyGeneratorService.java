package ca.nautarius.url.shortener.service.key;

import ca.nautarius.url.shortener.adapter.KeyGeneratorAdapter;
import ca.nautarius.url.shortener.exceptions.KeysExceedsAcceptedSizeException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@AllArgsConstructor
public class KeyGeneratorService {

    private final static BigInteger LIMIT = BigInteger.valueOf(10000000000L);

    private KeyGeneratorAdapter keyGeneratorAdapter;

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
