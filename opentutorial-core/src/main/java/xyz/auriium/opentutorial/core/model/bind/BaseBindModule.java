package xyz.auriium.opentutorial.core.model.bind;

import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.event.inner.CommonEventBus;
import xyz.auriium.opentutorial.core.event.inner.InnerEventBus;

public class BaseBindModule {

    public InnerEventBus innerEventBus(CommonEventBus bus) {
        return bus;
    }

}
