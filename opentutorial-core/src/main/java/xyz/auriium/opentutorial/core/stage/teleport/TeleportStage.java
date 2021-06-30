package xyz.auriium.opentutorial.core.stage.teleport;

import xyz.auriium.opentutorial.api.construct.Stage;

/**
 * Stage that teleports a player.
 */
public class TeleportStage implements Stage {

    private final String world;
    private final int x;
    private final int y;
    private final int z;
    private final int pitch;
    private final int yaw;

    public TeleportStage(String world, int x, int y, int z, int pitch, int yaw) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public String getWorld() {
        return world;
    }

    public int getPitch() {
        return pitch;
    }

    public int getYaw() {
        return yaw;
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
