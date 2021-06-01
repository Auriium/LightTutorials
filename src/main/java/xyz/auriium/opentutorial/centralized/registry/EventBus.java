package xyz.auriium.opentutorial.centralized.registry;

import xyz.auriium.opentutorial.centralized.Tutorial;

public interface EventBus {

    <E extends Event> void fire(E event, Tutorial tutorial);

    <E extends Event> void register(Class<E> clazz, EventConsumer<E> consumer);

}
