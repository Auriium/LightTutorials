package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.opentutorial.core.event.Event;
import xyz.auriium.opentutorial.core.event.InnerEventConsumer;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

/**
 * StageConsumer that is also an event consumer. Needs to be registered to both the registry and event handler.
 * @param <T> the type of stage
 * @param <E> the type of event
 */
public interface AwaitConsumer<T extends Stage,E extends Event> extends StageConsumer<T>, InnerEventConsumer<E> {

    /**
     * Method fired when stage is being processed AND correct event has been fired into the consumer.
     * @param stage the stage
     * @param event the event
     * @param tutorial the tutorial
     */
    void consume(T stage, E event, Tutorial tutorial);



}
