package xyz.auriium.opentutorial.core;

public class StageFailureException extends RuntimeException{

    public StageFailureException(String reason) {
        super(reason);
    }

}
