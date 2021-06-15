package xyz.auriium.opentutorial.core.stage.invisible;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

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