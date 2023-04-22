package ca.notarius.url.shortener.adapter;

import java.math.BigInteger;
import java.util.Optional;

public interface KeyGeneratorAdapter {

    Optional<BigInteger> getCurrent(String domain);

    BigInteger save(String domain, BigInteger key);

}
