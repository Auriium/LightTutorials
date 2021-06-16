package xyz.auriium.opentutorial.core;

/**
 * Exception signifying that the load of a stage failed
 */
public class StageFailureException extends RuntimeException{

    public StageFailureException(String reason) {
        super(reason);
    }

}
