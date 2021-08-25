package xyz.auriium.opentutorial.core.tutorial.stage.player;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.Identifiers;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;

import java.util.Map;

public class LockableStageInsertion implements StageInsertion {
    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new LockableConsumer(platform.lockable());
    }

    @Override
    public String identifier() {
        return "lock";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        boolean movement = Interpret.getRequired(Identifiers.LOCK_MOVE,map,FlexibleType::getBoolean);
        boolean view = Interpret.getAlternative(Identifiers.LOCK_VIEW,map,FlexibleType::getBoolean,false);

        return new LockableStage(movement,view);
    }
}
