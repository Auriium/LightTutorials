package me.aurium.opentutorial.stage.wait;

import me.aurium.opentutorial.stage.ConsumerSerializer;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

public class WaitSerializer implements ConsumerSerializer<WaitStage> {
    @Override
    public String identifier() {
        return "wait_stage";
    }

    @Override
    public WaitStage serialize(FlexibleType type) throws BadValueException {

        return null;
    }
}
