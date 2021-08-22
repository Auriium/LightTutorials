package xyz.auriium.opentutorial.core.stage.delay;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.api.construct.Stage;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.stage.Defaults;
import xyz.auriium.opentutorial.core.stage.Identifiers;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;

import java.util.Map;

public class ActionbarDelayStageInsertion implements StageInsertion {
    @Override
    public String identifier() {
        return "actionbar_delay";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        int delay = Interpret.getRequired(Identifiers.DELAY,map,FlexibleType::getInteger);
        String format = Interpret.getNullable(Identifiers.DELAYTYPE_FORMAT,map,FlexibleType::getString);

        return new ActionbarDelayStage(delay,format);
    }

    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new ActionbarDelayConsumer(platform.scheduler(), platform.userRegistry());
    }
}
