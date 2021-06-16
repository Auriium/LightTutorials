package xyz.auriium.opentutorial.spigot.stage;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

import java.util.Map;

public class LockStageSerializer implements StageSerializer<LockStage> {

    @Override
    public String identifier() {
        return "lock";
    }

    @Override
    public LockStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        boolean movement = Interpret.getRequired("lockMovement", map, FlexibleType::getBoolean);
        boolean view = Interpret.getEllusive("lockView", map, FlexibleType::getBoolean, false);

        return new LockStage(movement,view);
    }
}
