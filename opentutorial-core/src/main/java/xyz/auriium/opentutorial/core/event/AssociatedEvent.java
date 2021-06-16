package xyz.auriium.opentutorial.core.event;

import java.util.UUID;

public interface AssociatedEvent extends Event {

    UUID getAssociated();
}
