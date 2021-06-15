package xyz.auriium.opentutorial.core.stage.clickblock;

import xyz.auriium.opentutorial.core.event.AssociatedEvent;

import java.util.UUID;

public class ClickBlockEvent implements AssociatedEvent {

    private final UUID associated;

    private final int x;
    private final int y;
    private final int z;

    public ClickBlockEvent(UUID associated, int x, int y, int z) {
        this.associated = associated;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public UUID getAssociated() {
        return associated;
    }
}
