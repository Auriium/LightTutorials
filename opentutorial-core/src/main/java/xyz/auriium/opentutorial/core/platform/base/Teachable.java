package xyz.auriium.opentutorial.core.platform.base;

import java.util.UUID;

/**
 * Platform agnostic interface denoting something that can be served stages from a tutorial
 *
 * Not meant to be cached anywhere or held onto. Doing so is bad practice.
 */
public interface Teachable {

    UUID getID();
    String getName();

    void sendMessage(String string);
    void sendActionbar(String string);
    void sendTitle(String title, String subtitle, int a, int b, int c);

    void runAs(String command);
    void runConsole(String command);


    @Deprecated
    void teleport(int x, int y, int z);
    @Deprecated
    boolean teleport(int x, int y, int z, String world);
    @Deprecated
    void teleport(int x, int y, int z, int pitch, int yaw);
    @Deprecated
    boolean teleport(int x, int y, int z, int pitch, int yaw, String world);

    boolean teleport(PlatformlessLocation location);
    PlatformlessLocation getLocation();

    void setInvisible(boolean invisible);

    void setState(String state, boolean bool);
    boolean hasState(String state);

    void playSound(String sound, float volume, float pitch);

    void sendClickable(String message, String command, String hover);

}
