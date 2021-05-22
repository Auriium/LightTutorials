package me.aurium.opentutorial.centralized.registry;

import me.aurium.opentutorial.centralized.Tutorial;

public interface EventConsumer<E extends Event> {

    void consume(E event, Tutorial tutorial);

}
