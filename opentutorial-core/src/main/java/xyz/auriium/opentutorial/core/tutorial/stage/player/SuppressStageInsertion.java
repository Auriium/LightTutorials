package xyz.auriium.opentutorial.core.tutorial.stage.player;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.tutorial.Stage;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.Identifiers;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;

import java.util.Map;

public class SuppressStageInsertion implements StageInsertion {
    @Override
    public String identifier() {
        return "suppress";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        boolean x = Interpret.getRequired(Identifiers.SUPPRESS_CHAT,map,FlexibleType::getBoolean);

        return new SuppressStage(x);
    }

    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new SuppressStageConsumer(platform.suppressor());
    }
}
