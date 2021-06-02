package xyz.auriium.opentutorial.stage.spigot;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.stage.StageSerializer;

import java.util.Map;

public class ClickBlockSerializer implements StageSerializer<ClickBlockStage> {
    @Override
    public String identifier() {
        return "click_block";
    }

    @Override
    public ClickBlockStage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        return null;
    }
}
