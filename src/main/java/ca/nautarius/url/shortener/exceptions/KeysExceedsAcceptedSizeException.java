package ca.nautarius.url.shortener.exceptions;

public class KeysExceedsAcceptedSizeException extends RuntimeException {
    public KeysExceedsAcceptedSizeException(String message) {
        super(message);
    }
}
