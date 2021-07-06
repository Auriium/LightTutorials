package xyz.auriium.opentutorial.core.stage.age;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.Map;

public class AgeStageInsertion implements StageInsertion {

    AgeStageInsertion() {}

    public static AgeStageInsertion INIT = new AgeStageInsertion();

    @Override
    public StageConsumer<?> build(Platform<?> platform, ConfigController configController) {
        return new AgeStageConsumer(platform.scheduler(), platform.userRegistry(), configController.getMessageConfig());
    }

    @Override
    public String identifier() {
        return "age";
    }

    @Override
    public AgeStage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        Integer minimumAge = Interpret.getRequired("minimumAge",map, FlexibleType::getInteger);

        String runOnFail = Interpret.getNullable("runOnFailCommand", map, FlexibleType::getString);
        Long maxDelay = Interpret.getNullable("maxDelay",map,FlexibleType::getLong);
        boolean cancelOnFail = Interpret.getAlternative("cancelOnFail",map,FlexibleType::getBoolean, false);

        return new AgeStage(runOnFail, minimumAge,maxDelay,cancelOnFail);
    }
}
