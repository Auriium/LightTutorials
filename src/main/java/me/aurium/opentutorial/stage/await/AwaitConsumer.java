package me.aurium.opentutorial.stage.await;

import me.aurium.opentutorial.centralized.registry.Event;
import me.aurium.opentutorial.centralized.registry.EventConsumer;
import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.Stage;
import me.aurium.opentutorial.stage.StageConsumer;

/**
 * StageConsumer that is also an event consumer. Needs to be registered to both the registry and event handler.
 * @param <T>
 * @param <E>
 */
public interface AwaitConsumer<T extends Stage,E extends Event> extends StageConsumer<T>, EventConsumer<E> {

    void consume(T stage, E event, Tutorial tutorial);

    Class<E> eventClass();

}
