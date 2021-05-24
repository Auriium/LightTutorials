package me.aurium.opentutorial.stage.delay;

import me.aurium.opentutorial.centralized.config.Interpret;
import me.aurium.opentutorial.stage.StageSerializer;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

import java.util.Map;

public class DelaySerializer implements StageSerializer<DelayStage> {
    @Override
    public String identifier() {
        return "wait_stage";
    }

    @Override
    public DelayStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        long delay = Interpret.getRequired("delay",map,FlexibleType::getLong);

        return new DelayStage(delay);
    }
}
