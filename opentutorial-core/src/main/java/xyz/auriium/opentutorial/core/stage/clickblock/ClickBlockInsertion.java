package xyz.auriium.opentutorial.core.stage.clickblock;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;

public class ClickBlockInsertion implements StageInsertion {

    ClickBlockInsertion() {}

    public static ClickBlockInsertion INIT = new ClickBlockInsertion();

    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new ClickBlockConsumer(platform.scheduler(), platform.userRegistry(), configController.getMessageConfig());
    }

    @Override
    public String identifier() {
        return "click_block";
    }

    @Override
    public ClickBlockStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        int x = Interpret.getRequired("x",map,FlexibleType::getInteger);
        int y = Interpret.getRequired("y",map,FlexibleType::getInteger);
        int z = Interpret.getRequired("z",map,FlexibleType::getInteger);
        Long maxDelay = Interpret.getNullable("max_delay",map,FlexibleType::getLong);

        return new ClickBlockStage(x,y,z,maxDelay);
    }
}
