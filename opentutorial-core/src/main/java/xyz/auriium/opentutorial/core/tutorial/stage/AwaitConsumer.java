package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.opentutorial.core.event.Event;
import xyz.auriium.opentutorial.core.event.InnerEventConsumer;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

/**
 * StageConsumer that is also an event consumer. Needs to be registered to both the registry and event handler.
 * @param <T>
 * @param <E>
 */
public interface AwaitConsumer<T extends Stage,E extends Event> extends StageConsumer<T>, InnerEventConsumer<E> {

    void consume(T stage, E event, Tutorial tutorial);

    Class<E> eventClass();

}
