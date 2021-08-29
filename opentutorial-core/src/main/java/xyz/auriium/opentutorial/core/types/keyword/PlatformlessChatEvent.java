package xyz.auriium.opentutorial.core.types.keyword;

import xyz.auriium.opentutorial.core.event.AssociatedEvent;

import java.util.UUID;

public class PlatformlessChatEvent implements AssociatedEvent {

    private final UUID userID;
    private final String message;

    public PlatformlessChatEvent(UUID userID, String message) {
        this.userID = userID;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public UUID getAssociated() {
        return userID;
    }
}
