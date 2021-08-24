package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

public interface PlatformAccessProvider {

    void provideAndExecute(StageConsumer<?> consumer);


}
