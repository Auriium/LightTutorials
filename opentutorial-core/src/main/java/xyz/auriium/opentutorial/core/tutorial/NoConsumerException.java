package xyz.auriium.opentutorial.core.tutorial;

/**
 * Exception thrown when no consumer is present when one is expected.
 */
public class NoConsumerException extends RuntimeException{

    public NoConsumerException(String reason) {
        super(reason);
    }

}
