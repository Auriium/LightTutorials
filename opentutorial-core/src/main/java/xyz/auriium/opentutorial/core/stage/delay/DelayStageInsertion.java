package xyz.auriium.opentutorial.core.stage.delay;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;

public class DelayStageInsertion implements StageInsertion {

    DelayStageInsertion() {}

    public static DelayStageInsertion INIT = new DelayStageInsertion();


    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new DelayStageConsumer(platform.scheduler());
    }

    @Override
    public String identifier() {
        return "delay";
    }

    @Override
    public DelayStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        long delay = Interpret.getRequired("delay",map,FlexibleType::getLong);

        return new DelayStage(delay);
    }
}
