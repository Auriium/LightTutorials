package xyz.auriium.opentutorial.core.event;

import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public interface EventBus {

    <E> void fire(E event, Tutorial tutorial);

    <E> void register(Class<E> clazz, EventConsumer<E> consumer);

}
