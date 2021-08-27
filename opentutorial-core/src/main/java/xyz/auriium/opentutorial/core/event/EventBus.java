package xyz.auriium.opentutorial.core.event;

import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.UUID;

/**
 * Inner event bus backbone
 */
public interface EventBus {


    <E extends Event> void fire(E event, Tutorial tutorial);
    <E extends AssociatedEvent> void fire(E event);
    <E extends Event> void fire(E event, UUID tutorial);


}