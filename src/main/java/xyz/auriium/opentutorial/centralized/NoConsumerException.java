package xyz.auriium.opentutorial.centralized;

public class NoConsumerException extends RuntimeException{

    public NoConsumerException(String reason) {
        super(reason);
    }

}
