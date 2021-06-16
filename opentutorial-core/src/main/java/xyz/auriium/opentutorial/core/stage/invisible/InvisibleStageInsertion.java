package xyz.auriium.opentutorial.core.stage.invisible;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.tutorial.ConsumerInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

public class InvisibleStageInsertion implements ConsumerInsertion {

    InvisibleStageInsertion() {}

    public static InvisibleStageInsertion INIT = new InvisibleStageInsertion();


    @Override
    public StageConsumer<?> build(Platform platform, ConfigController configController) {
        return new InvisibleStageConsumer(platform.userRegistry());
    }
}
