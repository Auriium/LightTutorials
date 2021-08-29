package xyz.auriium.opentutorial.core;

/**
 * Exception signifying the load stage of the plugin failed
 */
public class LoadFailureException extends RuntimeException{

    public LoadFailureException(String reason) {
        super(reason);
    }

    public LoadFailureException(Throwable exception) {
        super(exception);
    }

}
