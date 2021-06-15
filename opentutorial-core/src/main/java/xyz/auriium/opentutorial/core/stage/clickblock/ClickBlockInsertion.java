package xyz.auriium.opentutorial.core.stage.clickblock;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.tutorial.ConsumerInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

public class ClickBlockInsertion implements ConsumerInsertion {

    ClickBlockInsertion() {}

    public static ClickBlockInsertion INIT = new ClickBlockInsertion();

    @Override
    public StageConsumer<?> build(Platform platform, ConfigController configController) {
        return new ClickBlockConsumer(platform.scheduler(), platform.userRegistry(), configController.getMessageConfig());
    }
}
