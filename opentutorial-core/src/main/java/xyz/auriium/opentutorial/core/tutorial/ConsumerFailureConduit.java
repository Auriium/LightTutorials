package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.opentutorial.core.consumer.StageException;

public interface ConsumerFailureConduit {

    void handle(Tutorial tutorial, FailureSupplier supplier);

    @FunctionalInterface
    interface FailureSupplier {
        void act() throws StageException;
    }
}
