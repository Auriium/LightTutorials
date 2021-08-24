package xyz.auriium.opentutorial.core.tutorial.stage.player;

import xyz.auriium.opentutorial.core.tutorial.Stage;
import xyz.auriium.opentutorial.core.platform.PlatformlessLocation;

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
