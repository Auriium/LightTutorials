package xyz.auriium.opentutorial.spigot.stage;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

import java.util.Map;

public class TeleportStageSerializer implements StageSerializer<TeleportStage> {
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

        return new TeleportStage(world,x,y,z);
    }
}
