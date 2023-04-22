package ca.nautarius.url.shortener.service.key;

import ca.nautarius.url.shortener.adapter.UrlShortenerAdapter;
import ca.nautarius.url.shortener.service.key.KeyGeneratorService;
import ca.nautarius.url.shortener.service.key.UrlKeyProviderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UrlKeyProviderServiceTest {

    private static final String CAREERS_PATH = "careers";
    private static final BigInteger EXISTING_KEY = BigInteger.valueOf(10000L);
    @InjectMocks
    private UrlKeyProviderService urlKeyProviderService;

    @Mock
    private UrlShortenerAdapter urlShortenerAdapter;

    @Mock
    private KeyGeneratorService keyGeneratorService;

    @Test
    void getAndIncrement_keyDoesNotExist() {
        given(urlShortenerAdapter.findByPath(CAREERS_PATH)).willReturn(Optional.empty());
        given(keyGeneratorService.getNextAndIncrement()).willReturn(BigInteger.ONE);
        BigInteger newKey = urlKeyProviderService.get(CAREERS_PATH);
        assertThat(newKey).isEqualTo(BigInteger.ONE);
    }

    @Test
    void getAndIncrement_keyDoesExist() {
        given(urlShortenerAdapter.findByPath(CAREERS_PATH)).willReturn(Optional.of(EXISTING_KEY));
        BigInteger newKey = urlKeyProviderService.get(CAREERS_PATH);
        verify(keyGeneratorService, never()).getNextAndIncrement();
        assertThat(newKey).isEqualTo(EXISTING_KEY);
    }

}