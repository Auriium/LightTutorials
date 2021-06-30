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

        String runOnFail = Interpret.getEllusive("runOnFailCommand", map, FlexibleType::getString,Interpret.NO_STRING);
        int minimumAge = Interpret.getRequired("minimumAge",map, FlexibleType::getInteger);
        int maxDelay = Interpret.getEllusive("maxDelay",map,FlexibleType::getInteger,Interpret.NO_INT);
        boolean cancelOnFail = Interpret.getEllusive("cancelOnFail",map,FlexibleType::getBoolean,true);

        return new AgeStage(runOnFail, minimumAge,maxDelay,cancelOnFail);
    }
}
