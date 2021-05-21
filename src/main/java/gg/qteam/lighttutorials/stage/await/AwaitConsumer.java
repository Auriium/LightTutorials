package gg.qteam.lighttutorials.stage.await;

import gg.qteam.lighttutorials.stage.Stage;
import gg.qteam.lighttutorials.stage.StageConsumer;
import space.arim.omnibus.events.Event;
import space.arim.omnibus.events.EventConsumer;

public interface AwaitConsumer<T extends Stage,E extends IdentifiableEvent> extends StageConsumer<T>, EventConsumer<E> {

    void consume(T stage, E event);

    Class<E> eventClass();

}
