package ca.notarius.url.shortener.service.key;

import ca.notarius.url.shortener.adapter.KeyGeneratorAdapter;
import ca.notarius.url.shortener.exceptions.KeysExceedsAcceptedSizeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KeyGeneratorServiceTest {

    private static final String DOMAIN = "https://www.notarius.com";
    @InjectMocks
    private KeyGeneratorService keyGeneratorService;

    @Mock
    private KeyGeneratorAdapter keyGeneratorAdapter;

    @Test
    void getNextAndIncrement_DoesNotExist() {
        given(keyGeneratorAdapter.getCurrent(DOMAIN)).willReturn(Optional.empty());

        BigInteger nextValue = keyGeneratorService.getNextAndIncrement(DOMAIN);

        verify(keyGeneratorAdapter).save(DOMAIN, BigInteger.TWO);
        assertThat(nextValue).isEqualTo(BigInteger.ONE);
    }

    @Test
    void getNextAndIncrement_Exist() {
        BigInteger expectedResult = BigInteger.valueOf(1999999999);
        given(keyGeneratorAdapter.getCurrent(DOMAIN)).willReturn(Optional.of(expectedResult));

        BigInteger nextValue = keyGeneratorService.getNextAndIncrement(DOMAIN);

        verify(keyGeneratorAdapter).save(DOMAIN, BigInteger.valueOf(2000000000));
        assertThat(nextValue).isEqualTo(expectedResult);
    }

    @Test
    void getNextAndIncrement_SizeExceedsAcceptedValues() {
        given(keyGeneratorAdapter.getCurrent(DOMAIN)).willReturn(Optional.of(BigInteger.valueOf(9999999999L)));

        assertThatThrownBy(() -> keyGeneratorService.getNextAndIncrement(DOMAIN))
                .isInstanceOf(KeysExceedsAcceptedSizeException.class)
                .hasMessage("key 10000000000 is too big");
    }

}