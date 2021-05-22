package me.aurium.opentutorial.centralized.event;

import me.aurium.opentutorial.centralized.Tutorial;

public interface EventBus {

    void fire(Event event, Tutorial tutorial);

    <E extends Event> void register(EventConsumer<E> consumer);

}
