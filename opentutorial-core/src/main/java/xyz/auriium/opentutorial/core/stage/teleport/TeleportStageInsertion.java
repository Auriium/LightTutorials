package xyz.auriium.opentutorial.core.stage.teleport;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.tutorial.ConsumerInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

public class TeleportStageInsertion implements ConsumerInsertion {

    TeleportStageInsertion() {}

    public static TeleportStageInsertion INIT = new TeleportStageInsertion();

    @Override
    public StageConsumer<?> build(Platform platform, ConfigController configController) {
        return new TeleportStageConsumer(platform.userRegistry());
    }
}
