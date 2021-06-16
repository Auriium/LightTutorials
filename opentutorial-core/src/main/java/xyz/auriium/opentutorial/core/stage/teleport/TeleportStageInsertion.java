package xyz.auriium.opentutorial.core.stage.teleport;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.ProcessingInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;

public class TeleportStageInsertion implements ProcessingInsertion {

    TeleportStageInsertion() {}

    public static TeleportStageInsertion INIT = new TeleportStageInsertion();

    @Override
    public StageConsumer<?> build(Platform platform, ConfigController configController) {
        return new TeleportStageConsumer(platform.userRegistry());
    }

    @Override
    public String identifier() {
        return "teleport";
    }

    @Override
    public TeleportStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        int x = Interpret.getRequired("x",map,FlexibleType::getInteger);
        int y = Interpret.getRequired("y",map,FlexibleType::getInteger);
        int z = Interpret.getRequired("z",map,FlexibleType::getInteger);
        String world = Interpret.getEllusive("world",map,FlexibleType::getString,Interpret.NO_STRING);
        int pitch = Interpret.getEllusive("pitch",map,FlexibleType::getInteger,Interpret.NO_INT);
        int yaw = Interpret.getEllusive("yaw",map,FlexibleType::getInteger,Interpret.NO_INT);

        return new TeleportStage(world,x,y,z, pitch, yaw);
    }
}
