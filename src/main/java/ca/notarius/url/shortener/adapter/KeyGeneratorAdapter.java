package ca.notarius.url.shortener.adapter;

import java.math.BigInteger;
import java.util.Optional;

public interface KeyGeneratorAdapter {

    /**
     * Get the current key for a specific domain.
     *
     * @param domain the domain ex: 'https://www.norarius.com'
     * @return the current key for this domain
     */
    Optional<BigInteger> getCurrent(String domain);

    /**
     * Save the key for a specific domain.
     *
     * @param domain the domain ex: 'https://www.norarius.com'
     * @param key    the key
     * @return the key
     */
    BigInteger save(String domain, BigInteger key);

}
