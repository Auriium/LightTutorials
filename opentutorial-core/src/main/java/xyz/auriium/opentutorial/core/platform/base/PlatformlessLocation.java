package xyz.auriium.opentutorial.core.platform.base;

import java.util.Optional;

public class PlatformlessLocation {

    private final double x;
    private final double y;
    private final double z;

    private final Float pitch;
    private final Float yaw;

    private final String world;

    public PlatformlessLocation(double x, double y, double z, Float pitch, Float yaw, String world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.world = world;
    }

    public PlatformlessLocation(double x, double y, double z, String world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.pitch = null;
        this.yaw = null;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Float getPitch(float alternative) {
        return pitch == null ? alternative : pitch;
    }

    public Float getYaw(float alternative) {
        return yaw == null ? alternative : yaw;
    }

    public Optional<String> getWorld() {
        return Optional.ofNullable(world);
    }
}
