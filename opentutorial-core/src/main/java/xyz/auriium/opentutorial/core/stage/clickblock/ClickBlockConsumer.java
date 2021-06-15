package xyz.auriium.opentutorial.core.stage.clickblock;

import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.core.config.ConfigHolder;
import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.AbstractDelayConsumer;

public class ClickBlockConsumer extends AbstractDelayConsumer<ClickBlockStage,ClickBlockEvent> {

    ClickBlockConsumer(Scheduler scheduler, TeachableRegistry registry, MessageConfig config) {
        super(scheduler, registry, config);
    }

    @Override
    public Class<ClickBlockStage> stageClass() {
        return ClickBlockStage.class;
    }

    @Override
    public void consume(ClickBlockStage stage, ClickBlockEvent event, Tutorial tutorial) {
        if (event.getX() == stage.getX() && event.getY() == stage.getY() && event.getZ() == stage.getZ()) {
            tutorial.fireNext();
        }
    }

    @Override
    public Class<ClickBlockEvent> eventClass() {
        return ClickBlockEvent.class;
    }
}
