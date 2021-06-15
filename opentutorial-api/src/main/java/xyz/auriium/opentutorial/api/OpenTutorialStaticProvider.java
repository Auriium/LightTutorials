package xyz.auriium.opentutorial.api;

public class OpenTutorialStaticProvider { //i hate this class

    private static volatile OpenTutorial instance;

    public static OpenTutorial get() {
        return instance;
    }

}
