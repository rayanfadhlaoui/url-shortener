package ca.nautarius.url.shortener.adapter;

import java.math.BigInteger;

public interface KeyGeneratorAdapter {

    BigInteger getCurrent(String domain);

    BigInteger save(String domain, BigInteger key);

}
