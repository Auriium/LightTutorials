package xyz.auriium.opentutorial.core;

public class MissingServiceException extends RuntimeException {

    public MissingServiceException(String message) {
        super(message);
    }
}
