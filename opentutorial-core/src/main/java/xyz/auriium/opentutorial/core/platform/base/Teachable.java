package xyz.auriium.opentutorial.core.platform.base;

/**
 * Platform agnostic interface denoting something that can be served stages from a tutorial
 */
public interface Teachable {

    String getName();

    void sendMessage(String string);
    void sendActionbar(String string);
    void sendTitle(String title, String subtitle, int a, int b, int c);

    void runAs(String command);
    void runConsole(String command);

    void teleport(int x, int y, int z);
    boolean teleport(int x, int y, int z, String world);

    void setInvisible(boolean invisible);

}
