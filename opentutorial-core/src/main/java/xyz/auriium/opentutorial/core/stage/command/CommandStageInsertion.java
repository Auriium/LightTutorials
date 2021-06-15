package xyz.auriium.opentutorial.core.stage.command;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.tutorial.ConsumerInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

public class CommandStageInsertion implements ConsumerInsertion {

    CommandStageInsertion() {}

    public static CommandStageInsertion INIT = new CommandStageInsertion();

    @Override
    public StageConsumer<?> build(Platform platform, ConfigController configController) {
        return new CommandStageConsumer(platform.userRegistry());
    }
}
