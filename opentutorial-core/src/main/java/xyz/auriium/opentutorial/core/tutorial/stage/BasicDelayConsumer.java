package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.platform.Scheduler;
import xyz.auriium.opentutorial.core.platform.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public abstract class BasicDelayConsumer<T extends AwaitStage,E> extends DelayConsumer<T,E> {

    protected BasicDelayConsumer(TeachableRegistry registry, Scheduler scheduler, MessageConfig config) {
        super(registry, scheduler, config);
    }

    @Override
    protected void started1(T options, Tutorial continuable) {

    }


}
