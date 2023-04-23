package ca.notarius.url.shortener.adapter.database.jpa;

import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import ca.notarius.url.shortener.model.UrlEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@DataJpaTest
class UrlShortenerDatabaseRepositoryJpaTest {
    private static final String DOMAIN = "https://www.notarius.com";
    private static final String PATH = "/careers";
    private static final BigInteger EXPECTED_KEY = BigInteger.valueOf(2560);
    @Autowired
    private UrlShortenerDatabaseRepository urlShortenerDatabaseRepository;

    @Test
    void findByDomainAndPath() {
        var shortenedUrlEntity = createShortenedUrlEntity();

        urlShortenerDatabaseRepository.save(shortenedUrlEntity);

        Optional<BigInteger> optionalKey = urlShortenerDatabaseRepository.findByDomainAndPath(DOMAIN, PATH);

        assertThat(optionalKey.isPresent()).isTrue();
        assertThat(optionalKey.get()).isEqualTo(EXPECTED_KEY);
    }

    @Test
    void findByDomainAndPath_NoMatch() {
        var shortenedUrlEntity = createShortenedUrlEntity();

        urlShortenerDatabaseRepository.save(shortenedUrlEntity);

        Optional<BigInteger> optionalKey = urlShortenerDatabaseRepository.findByDomainAndPath(DOMAIN, "broken");

        assertThat(optionalKey.isEmpty()).isTrue();

        optionalKey = urlShortenerDatabaseRepository.findByDomainAndPath("broken", PATH);

        assertThat(optionalKey.isEmpty()).isTrue();
    }

    @Test
    void findPathByDomainAndKey() {
        var shortenedUrlEntity = createShortenedUrlEntity();

        urlShortenerDatabaseRepository.save(shortenedUrlEntity);

        Optional<String> optionalPath = urlShortenerDatabaseRepository.findPathByDomainAndKey(DOMAIN, EXPECTED_KEY);

        assertThat(optionalPath.isPresent()).isTrue();
        assertThat(optionalPath.get()).isEqualTo(PATH);
    }

    @Test
    void findPathByDomainAndKey_NoMatch() {
        var shortenedUrlEntity = createShortenedUrlEntity();

        urlShortenerDatabaseRepository.save(shortenedUrlEntity);

        Optional<String> optionalPath = urlShortenerDatabaseRepository.findPathByDomainAndKey(DOMAIN, BigInteger.ZERO);

        assertThat(optionalPath.isEmpty()).isTrue();
    }

    private static ShortenedUrlEntity createShortenedUrlEntity() {
        var shortenedUrlEntity = new ShortenedUrlEntity();
        var urlEntity = new UrlEntity();
        urlEntity.setRootValue(DOMAIN);
        shortenedUrlEntity.setRoot(urlEntity);
        shortenedUrlEntity.setOriginalPath(PATH);
        shortenedUrlEntity.setPathKey(EXPECTED_KEY);
        return shortenedUrlEntity;
    }
}