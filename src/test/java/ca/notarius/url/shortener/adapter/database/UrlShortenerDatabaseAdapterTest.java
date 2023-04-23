package ca.notarius.url.shortener.adapter.database;

import ca.notarius.url.shortener.adapter.database.jpa.UrlShortenerDatabaseRepository;
import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UrlShortenerDatabaseAdapterTest {
    private static final String PATH = "/career";
    private static final String DOMAIN = "https://www.notarius.com";
    private static final BigInteger KEY = BigInteger.valueOf(123);
    @InjectMocks
    private UrlShortenerDatabaseAdapter urlShortenerDatabaseAdapter;

    @Mock
    private UrlShortenerDatabaseRepository urlShortenerDatabaseRepository;

    @Mock
    private ShortenedUrlEntity shortenedUrlEntity;

    @Mock
    private ShortenedUrlEntity newShortenedUrlEntity;

    @Test
    void save() {
        given(urlShortenerDatabaseRepository.save(shortenedUrlEntity)).willReturn(newShortenedUrlEntity);
        ShortenedUrlEntity savedShortenedUrlEntity = urlShortenerDatabaseAdapter.save(shortenedUrlEntity);

        assertThat(savedShortenedUrlEntity).isEqualTo(newShortenedUrlEntity);
    }

    @Test
    void findByDomainAndPath() {
        given(urlShortenerDatabaseRepository.findByDomainAndPath(DOMAIN, PATH)).willReturn(Optional.of(KEY));
        Optional<BigInteger> optionalKey = urlShortenerDatabaseAdapter.findByDomainAndPath(DOMAIN, PATH);

        assertThat(optionalKey.isPresent()).isTrue();
        assertThat(optionalKey.get()).isEqualTo(KEY);
    }

    @Test
    void findPathByDomainAndKey() {
        given(urlShortenerDatabaseRepository.findPathByDomainAndKey(DOMAIN, KEY)).willReturn(Optional.of(PATH));
        Optional<String> actualPath = urlShortenerDatabaseAdapter.findPathByDomainAndKey(DOMAIN, KEY);

        assertThat(actualPath.isPresent()).isTrue();
        assertThat(actualPath.get()).isEqualTo(PATH);
    }
}