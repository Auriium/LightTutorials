package xyz.auriium.opentutorial.core.event;

import java.util.UUID;

public interface OuterEventBus {

    <E extends AssociatedEvent> void fire(E event);
    <E extends Event> void fire(E event, UUID tutorial);

}
