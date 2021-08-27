package xyz.auriium.opentutorial.core.types.player;

import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.openmineplatform.api.binding.location.PlatformLocation;

/**
 * Stage that teleports a player.
 */
public class TeleportStage implements Stage {

    private final PlatformLocation location;

    public TeleportStage(PlatformLocation location) {
        this.location = location;
    }

    public PlatformLocation getLocation() {
        return location;
    }
}
