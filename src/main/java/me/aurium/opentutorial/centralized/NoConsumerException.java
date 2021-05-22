package me.aurium.opentutorial.centralized;

public class NoConsumerException extends RuntimeException{

    public NoConsumerException(String reason) {
        super(reason);
    }

}
