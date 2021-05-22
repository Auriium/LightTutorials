package me.aurium.opentutorial.centralized.registry;

import me.aurium.opentutorial.centralized.Tutorial;

public interface EventBus {

    <E extends Event> void fire(E event, Tutorial tutorial);

    <E extends Event> void register(Class<E> clazz, EventConsumer<E> consumer);

}
