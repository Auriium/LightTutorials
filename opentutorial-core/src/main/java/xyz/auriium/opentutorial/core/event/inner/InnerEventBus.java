package xyz.auriium.opentutorial.core.event.inner;

import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public interface InnerEventBus {

    <E> void fire(E event, Tutorial tutorial);

    <E> void register(Class<E> clazz, EventConsumer<E> consumer);

}
