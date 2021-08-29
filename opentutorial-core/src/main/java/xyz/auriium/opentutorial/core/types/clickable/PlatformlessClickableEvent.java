package xyz.auriium.opentutorial.core.types.clickable;

import xyz.auriium.opentutorial.core.event.AssociatedEvent;

import java.util.UUID;

public class PlatformlessClickableEvent implements AssociatedEvent {

    private final int clicked;
    private final UUID clicker;

    public PlatformlessClickableEvent(int clicked, UUID clicker) {
        this.clicked = clicked;
        this.clicker = clicker;
    }

    public int getClicked() {
        return clicked;
    }

    @Override
    public UUID getAssociated() {
        return clicker;
    }
}
