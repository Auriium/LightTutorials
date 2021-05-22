package me.aurium.opentutorial.centralized.config;

import me.aurium.opentutorial.stage.Stage;
import me.aurium.opentutorial.stage.response.ResponseStage;
import me.aurium.opentutorial.stage.wait.WaitStage;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.Decomposer;
import space.arim.dazzleconf.serialiser.FlexibleType;
import space.arim.dazzleconf.serialiser.ValueSerialiser;

public class StageSerializer implements ValueSerialiser<Stage> {

    @Override
    public Class<Stage> getTargetClass() {
        return Stage.class;
    }

    @Override
    public Stage deserialise(FlexibleType flexibleType) throws BadValueException {

        if (flexibleType.getString().equals("test")) {
            return new ResponseStage(null,1,false);
        } else if (flexibleType.getString().equals("help")) {
            return new WaitStage(2L);
        }

        return null;
    }

    @Override
    public Object serialise(Stage stage, Decomposer decomposer) {
        return null;
    }
}
