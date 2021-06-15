package xyz.auriium.opentutorial.core.event;

import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.UUID;

/**
 * Inner event bus backbone
 */
public interface InnerEventBus {


    <E extends Event> void fire(E event, Tutorial tutorial);

    <E extends Event> void register(Class<E> clazz, InnerEventConsumer<E> consumer);


}
