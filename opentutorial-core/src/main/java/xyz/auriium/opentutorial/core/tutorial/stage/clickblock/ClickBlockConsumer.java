package xyz.auriium.opentutorial.core.tutorial.stage.clickblock;

import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.event.chat.PlatformlessBlockEvent;
import xyz.auriium.opentutorial.core.platform.Scheduler;
import xyz.auriium.opentutorial.core.platform.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.BasicDelayConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.DelayConsumer;

/**
 * Consumer system that handles {@link ClickBlockStage}
 */
public class ClickBlockConsumer extends BasicDelayConsumer<ClickBlockStage, PlatformlessBlockEvent> {

    ClickBlockConsumer(TeachableRegistry registry, Scheduler scheduler, MessageConfig config) {
        super(registry, scheduler, config);
    }

    @Override
    public Class<ClickBlockStage> stageClass() {
        return ClickBlockStage.class;
    }

    @Override
    public void consume(ClickBlockStage stage, PlatformlessBlockEvent event, Tutorial tutorial) {
        if (event.getX() == stage.getX() && event.getY() == stage.getY() && event.getZ() == stage.getZ()) {
            tutorial.fireNext();
        }
    }

    @Override
    public Class<PlatformlessBlockEvent> eventClass() {
        return PlatformlessBlockEvent.class;
    }
}
