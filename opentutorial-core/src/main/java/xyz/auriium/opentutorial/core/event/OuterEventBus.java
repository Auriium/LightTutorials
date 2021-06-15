package xyz.auriium.opentutorial.core.event;

import xyz.auriium.opentutorial.core.event.hook.HookRegistry;

import java.util.UUID;

public interface OuterEventBus {

    <E extends AssociatedEvent> void fire(E event);
    <E extends Event> void fire(E event, UUID tutorial);

}
