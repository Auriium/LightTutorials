package xyz.auriium.opentutorial.stage.delay;

import xyz.auriium.opentutorial.centralized.config.tutorials.Interpret;
import xyz.auriium.opentutorial.stage.StageSerializer;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

import java.util.Map;

public class DelaySerializer implements StageSerializer<DelayStage> {
    @Override
    public String identifier() {
        return "wait";
    }

    @Override
    public DelayStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        long delay = Interpret.getRequired("delay",map,FlexibleType::getLong);

        return new DelayStage(delay);
    }
}
