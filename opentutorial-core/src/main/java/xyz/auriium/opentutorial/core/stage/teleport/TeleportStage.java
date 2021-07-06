package xyz.auriium.opentutorial.core.stage.teleport;

import xyz.auriium.opentutorial.api.construct.Stage;
import xyz.auriium.opentutorial.core.platform.base.PlatformlessLocation;

/**
 * Stage that teleports a player.
 */
public class TeleportStage implements Stage {

    private final PlatformlessLocation location;

    public TeleportStage(PlatformlessLocation location) {
        this.location = location;
    }

    public PlatformlessLocation getLocation() {
        return location;
    }
}
