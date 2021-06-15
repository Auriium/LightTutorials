package xyz.auriium.opentutorial.core.platform.base;

public interface Audience {

    String getName();

    void sendMessage(String string);
    void sendActionbar(String string);
    void sendTitle(String title, String subtitle, int a, int b, int c);

    void runAs(String command);
    void runConsole(String command);

}
