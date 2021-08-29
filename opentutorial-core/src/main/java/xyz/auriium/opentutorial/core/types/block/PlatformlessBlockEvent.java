package xyz.auriium.opentutorial.core.types.block;

import xyz.auriium.opentutorial.core.event.AssociatedEvent;

import java.util.UUID;

public class PlatformlessBlockEvent implements AssociatedEvent {

    private final UUID uuid;
    private final int x;
    private final int y;
    private final int z;

    public PlatformlessBlockEvent(UUID uuid, int x, int y, int z) {
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public UUID getAssociated() {
        return uuid;
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
}
