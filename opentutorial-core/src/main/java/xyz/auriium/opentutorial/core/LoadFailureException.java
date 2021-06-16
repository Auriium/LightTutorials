package xyz.auriium.opentutorial.core;

public class LoadFailureException extends RuntimeException{

    public LoadFailureException(String reason) {
        super(reason);
    }

    public LoadFailureException(Throwable exception) {
        super(exception);
    }

}
