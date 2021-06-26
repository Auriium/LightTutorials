package xyz.auriium.opentutorial.core.stage.lock;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.ProcessingInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;

public class LockableStageInsertion implements ProcessingInsertion {
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
        boolean movement = Interpret.getRequired("lock_movement",map,FlexibleType::getBoolean);
        boolean view = Interpret.getEllusive("lock_view",map,FlexibleType::getBoolean,false);

        return new LockableStage(movement,view);
    }
}
