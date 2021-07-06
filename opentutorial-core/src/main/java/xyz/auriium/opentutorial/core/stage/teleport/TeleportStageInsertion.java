package xyz.auriium.opentutorial.core.stage.teleport;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.platform.base.PlatformlessLocation;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;

public class TeleportStageInsertion implements StageInsertion {

    TeleportStageInsertion() {}

    public static TeleportStageInsertion INIT = new TeleportStageInsertion();

    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new TeleportStageConsumer(platform.userRegistry());
    }

    @Override
    public String identifier() {
        return "teleport";
    }

    @Override
    public TeleportStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        Double x = Interpret.getRequired("x",map,FlexibleType::getDouble);
        Double y = Interpret.getRequired("y",map,FlexibleType::getDouble);
        Double z = Interpret.getRequired("z",map,FlexibleType::getDouble);
        String world = Interpret.getNullable("world",map,FlexibleType::getString);
        Float pitch = Interpret.getNullable("pitch",map,FlexibleType::getFloat);
        Float yaw = Interpret.getNullable("yaw",map,FlexibleType::getFloat);

        return new TeleportStage(new PlatformlessLocation(x, y, z, pitch, yaw, world));
    }
}
