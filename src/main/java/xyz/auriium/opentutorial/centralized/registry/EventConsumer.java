package xyz.auriium.opentutorial.centralized.registry;

import xyz.auriium.opentutorial.centralized.Tutorial;

public interface EventConsumer<E extends Event> {

    void consume(E event, Tutorial tutorial);

}
