package xyz.auriium.opentutorial.core.event.chat;

import xyz.auriium.opentutorial.core.event.AssociatedEvent;
import xyz.auriium.opentutorial.core.platform.Teachable;

import java.util.UUID;

public class PlatformlessChatEvent implements AssociatedEvent {

    private final Teachable player;
    private final String message;

    public PlatformlessChatEvent(Teachable player, String message) {
        this.player = player;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Teachable getPlayer() {
        return player;
    }


    @Override
    public UUID getAssociated() {
        return player.getID();
    }
}
