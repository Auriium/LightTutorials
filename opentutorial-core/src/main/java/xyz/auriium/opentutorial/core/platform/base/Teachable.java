package xyz.auriium.opentutorial.core.platform.base;

import java.util.UUID;

/**
 * Platform agnostic interface denoting something that can be served stages from a tutorial
 */
public interface Teachable {

    UUID getID();
    String getName();

    void sendMessage(String string);
    void sendActionbar(String string);
    void sendTitle(String title, String subtitle, int a, int b, int c);

    void runAs(String command);
    void runConsole(String command);

    void teleport(int x, int y, int z);
    boolean teleport(int x, int y, int z, String world);

    void setInvisible(boolean invisible);

    void setState(String state, boolean bool);
    boolean hasState(String state);

}
