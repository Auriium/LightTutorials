package xyz.auriium.opentutorial.core.tutorial.stage.clickblock;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.Identifiers;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;

import java.util.Map;

public class ClickBlockInsertion implements StageInsertion {

    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new ClickBlockConsumer(platform.userRegistry(), platform.scheduler(), configController.getMessageConfig());
    }

    @Override
    public String identifier() {
        return "click_block";
    }

    @Override
    public ClickBlockStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        int x = Interpret.getRequired(Identifiers.LOC_X,map,FlexibleType::getInteger);
        int y = Interpret.getRequired(Identifiers.LOC_Y,map,FlexibleType::getInteger);
        int z = Interpret.getRequired(Identifiers.LOC_Z,map,FlexibleType::getInteger);
        Integer maxDelay = Interpret.getNullable(Identifiers.DELAYTYPE_MAX_DELAY,map,FlexibleType::getInteger);
        String actionbarFormat = Interpret.getNullable(Identifiers.DELAYTYPE_FORMAT, map, FlexibleType::getString);

        return new ClickBlockStage(x,y,z,maxDelay,actionbarFormat);
    }
}
