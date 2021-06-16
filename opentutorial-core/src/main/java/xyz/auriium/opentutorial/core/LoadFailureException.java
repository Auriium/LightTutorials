package xyz.auriium.opentutorial.core;

/**
 * Exception signifying the load stage of the {@link xyz.auriium.opentutorial.core.platform.impl.PlatformDependentLoader} failed
 */
public class LoadFailureException extends RuntimeException{

    public LoadFailureException(String reason) {
        super(reason);
    }

    public LoadFailureException(Throwable exception) {
        super(exception);
    }

}
