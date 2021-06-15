package xyz.auriium.opentutorial.core.stage.clickblock;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
import xyz.auriium.opentutorial.core.tutorial.stage.StageSerializer;

import java.util.Map;

public class ClickBlockSerializer implements StageSerializer<ClickBlockStage> {
    @Override
    public String identifier() {
        return "click_block";
    }

    @Override
    public ClickBlockStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        int x = Interpret.getRequired("x",map,FlexibleType::getInteger);
        int y = Interpret.getRequired("y",map,FlexibleType::getInteger);
        int z = Interpret.getRequired("z",map,FlexibleType::getInteger);
        int maxDelay = Interpret.getEllusive("maxDelay",map,FlexibleType::getInteger,Interpret.NO_INT);

        return new ClickBlockStage(x,y,z,maxDelay);
    }
}
