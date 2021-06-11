package xyz.auriium.opentutorial.core.event.inner;

import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public interface EventConsumer<E> {

    void consume(E event, Tutorial tutorial);

}
