package ca.notarius.url.shortener.exceptions;

/**
 * The InvalidPathException is thrown if the path of an url is not valid.
 */
public class InvalidPathException extends Throwable {
    public InvalidPathException(String message) {
        super(message);
    }
}
