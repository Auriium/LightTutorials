package xyz.auriium.opentutorial.core.event;

import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public interface InnerEventConsumer<E extends Event> {

    void consume(E event, Tutorial tutorial);

    Class<E> eventClass();

}
