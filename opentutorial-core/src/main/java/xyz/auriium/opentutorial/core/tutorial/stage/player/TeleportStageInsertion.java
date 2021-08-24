package xyz.auriium.opentutorial.core.tutorial.stage.player;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.platform.PlatformlessLocation;
import xyz.auriium.opentutorial.core.tutorial.stage.Identifiers;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;

import java.util.Map;

public class TeleportStageInsertion implements StageInsertion {

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

        Double x = Interpret.getRequired(Identifiers.LOC_X,map,FlexibleType::getDouble);
        Double y = Interpret.getRequired(Identifiers.LOC_Y,map,FlexibleType::getDouble);
        Double z = Interpret.getRequired(Identifiers.LOC_Z,map,FlexibleType::getDouble);
        String world = Interpret.getNullable(Identifiers.LOC_WORLD,map,FlexibleType::getString);
        Float pitch = Interpret.getNullable(Identifiers.LOC_PITCH,map,FlexibleType::getFloat);
        Float yaw = Interpret.getNullable(Identifiers.LOC_YAW,map,FlexibleType::getFloat);

        return new TeleportStage(new PlatformlessLocation(x, y, z, pitch, yaw, world));
    }
}
