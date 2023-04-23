package ca.notarius.url.shortener.exceptions;

/**
 * The KeysExceedsAcceptedSizeException is for when a key exceed the accepted size.
 */
public class KeysExceedsAcceptedSizeException extends RuntimeException {
    public KeysExceedsAcceptedSizeException(String message) {
        super(message);
    }
}
