package xyz.auriium.opentutorial.spigot.stage;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.types.tutorials.Interpret;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

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
