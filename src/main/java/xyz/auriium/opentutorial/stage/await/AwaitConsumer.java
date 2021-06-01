package xyz.auriium.opentutorial.stage.await;

import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.centralized.registry.Event;
import xyz.auriium.opentutorial.centralized.registry.EventConsumer;
import xyz.auriium.opentutorial.stage.Stage;
import xyz.auriium.opentutorial.stage.StageConsumer;

/**
 * StageConsumer that is also an event consumer. Needs to be registered to both the registry and event handler.
 * @param <T>
 * @param <E>
 */
public interface AwaitConsumer<T extends Stage,E extends Event> extends StageConsumer<T>, EventConsumer<E> {

    void consume(T stage, E event, Tutorial tutorial);

    Class<E> eventClass();

}
