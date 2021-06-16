package xyz.auriium.opentutorial.core.stage.age;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.tutorial.ConsumerInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

public class AgeStageInsertion implements ConsumerInsertion {

    AgeStageInsertion() {}

    public static AgeStageInsertion INIT = new AgeStageInsertion();

    @Override
    public StageConsumer<?> build(Platform platform, ConfigController configController) {
        return new AgeStageConsumer(platform.scheduler(), platform.userRegistry(), configController.getMessageConfig());
    }
}
