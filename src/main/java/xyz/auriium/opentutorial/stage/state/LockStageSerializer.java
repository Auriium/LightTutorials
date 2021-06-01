package xyz.auriium.opentutorial.stage.state;

import xyz.auriium.opentutorial.centralized.config.tutorials.Interpret;
import xyz.auriium.opentutorial.stage.StageSerializer;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

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
