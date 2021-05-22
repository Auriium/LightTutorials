package me.aurium.opentutorial.stage.await;

import me.aurium.opentutorial.centralized.event.Event;
import me.aurium.opentutorial.centralized.event.EventConsumer;
import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.Stage;
import me.aurium.opentutorial.stage.StageConsumer;

public interface AwaitConsumer<T extends Stage,E extends Event> extends StageConsumer<T>, EventConsumer<E> {

    void consume(T stage, E event, Tutorial tutorial);

    Class<E> eventClass();

}
