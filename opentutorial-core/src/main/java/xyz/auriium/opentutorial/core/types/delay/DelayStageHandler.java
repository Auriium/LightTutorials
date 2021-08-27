package xyz.auriium.opentutorial.core.types.delay;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.openmineplatform.api.scheduling.SchedulerTask;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.BasicConsumer;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.consumer.stage.StageLocalValue;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.Map;
import java.util.UUID;

public class DelayStageHandler implements BasicConsumer<DelayStage> {
    @Override
    public String identifier() {
        return "delay";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        long delay = Interpret.getRequired(Identifiers.DELAY,map,FlexibleType::getLong);

        return new DelayStage(delay);
    }

    @Override
    public void stageStarted(DelayStage options, Tutorial tutorial) {
        SchedulerTask task = tutorial.getPlatform()
                .scheduler()
                .runLater(tutorial::fireNext, options.getDelay());

        tutorial.localStorage().register("delayTask", new StageLocalValue<>(task, true, val -> task.cancel()));
    }

    @Override
    public Class<DelayStage> stageClass() {
        return DelayStage.class;
    }
}
