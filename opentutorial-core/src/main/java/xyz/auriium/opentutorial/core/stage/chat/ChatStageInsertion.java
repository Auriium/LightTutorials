package xyz.auriium.opentutorial.core.stage.chat;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.ConsumerInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

public class ChatStageInsertion implements ConsumerInsertion {
    @Override
    public StageConsumer<?> build(Platform platform, ConfigController configController) {
        return new ChatStageConsumer(platform.userRegistry());
    }
}