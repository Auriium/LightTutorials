package xyz.auriium.opentutorial.core.types.delay;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.openmineplatform.api.interfaceable.user.User;
import xyz.auriium.openmineplatform.api.scheduling.SchedulerTask;
import xyz.auriium.opentutorial.core.StageExceptionMapper;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.BasicConsumer;
import xyz.auriium.opentutorial.core.consumer.RepeatedRunnable;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.consumer.stage.StageLocalValue;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.Map;

public class ActionbarDelayStageHandler implements BasicConsumer<ActionbarDelayStage> {
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
    public void stageStarted(ActionbarDelayStage options, Tutorial tutorial) {
        User user = tutorial.getPlatform()
                .interRegistry()
                .getTelescoping(tutorial.getIdentifier(), StageExceptionMapper.USER);

        SchedulerTask task = tutorial
                .getPlatform()
                .scheduler()
                .runRepeated(new RepeatedRunnable(user, options, tutorial), 2L);

        tutorial.localStorage().register("actionbarTask", new StageLocalValue<>(task, true, val -> task.cancel()));
    }

    @Override
    public Class<ActionbarDelayStage> stageClass() {
        return ActionbarDelayStage.class;
    }
}
