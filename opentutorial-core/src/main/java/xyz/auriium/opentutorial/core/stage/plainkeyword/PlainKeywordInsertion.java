package xyz.auriium.opentutorial.core.stage.plainkeyword;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.tutorial.ConsumerInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

public class PlainKeywordInsertion implements ConsumerInsertion {
    @Override
    public StageConsumer<?> build(Platform platform, ConfigController configController) {
        return new PlainKeywordStageConsumer(platform.scheduler(), platform.userRegistry(), configController.getMessageConfig());
    }
}
