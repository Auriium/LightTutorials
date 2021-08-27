package xyz.auriium.opentutorial.core.consumer;

import xyz.auriium.opentutorial.core.consumer.StageConsumer;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public interface EventStageConsumer<T extends Stage,E> extends StageConsumer<T,E> {

    @Override
    default boolean isRegisterable() {
        return true;
    }

    /**
     * Method fired when stage is being processed and a correct event has been fired into the consumer.
     * @param stage the stage
     * @param event the event
     * @param tutorial the tutorial
     */
    void consume(T stage, E event, Tutorial tutorial);

    void started1(T stage, Tutorial tutorial);

}
