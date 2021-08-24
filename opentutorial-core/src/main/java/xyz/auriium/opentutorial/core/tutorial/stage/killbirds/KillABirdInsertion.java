package xyz.auriium.opentutorial.core.tutorial.stage.killbirds;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.tutorial.Stage;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;

import java.util.Map;

public class KillABirdInsertion implements StageInsertion {
    @Override
    public String identifier() {
        return "killabird";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        int birdNum = Interpret.getRequired("bird",map,FlexibleType::getInteger);

        return new KillABirdStage(birdNum);
    }

    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new KillABirdConsumer();
    }
}
