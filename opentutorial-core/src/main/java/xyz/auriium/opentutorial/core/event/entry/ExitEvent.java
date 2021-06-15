package xyz.auriium.opentutorial.core.event.entry;

import xyz.auriium.opentutorial.core.event.AssociatedEvent;

import java.util.UUID;

public class ExitEvent implements AssociatedEvent {

    private final UUID uuid;

    public ExitEvent(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID getAssociated() {
        return uuid;
    }

}
