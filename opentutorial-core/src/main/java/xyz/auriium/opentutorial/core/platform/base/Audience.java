package xyz.auriium.opentutorial.core.platform.base;

public interface Audience {

    String getName();

    void sendMessage(String string);
    void runAs(String command);
    void runConsole(String command);

}
