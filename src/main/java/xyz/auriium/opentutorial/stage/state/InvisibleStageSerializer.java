package xyz.auriium.opentutorial.stage.state;

import xyz.auriium.opentutorial.centralized.config.tutorials.Interpret;
import xyz.auriium.opentutorial.stage.StageSerializer;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

import java.util.Map;

public class InvisibleStageSerializer implements StageSerializer<InvisibleStage> {

    @Override
    public String identifier() {
        return "invisible";
    }

    @Override
    public InvisibleStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        boolean enabled = Interpret.getRequired("isInvisible", map, FlexibleType::getBoolean);

        return new InvisibleStage(enabled);
    }
}
