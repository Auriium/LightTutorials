package xyz.auriium.opentutorial.core.platform.impl;

import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.platform.base.Colorer;
import xyz.auriium.opentutorial.core.platform.base.PlatformDetail;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.UserRegistry;

import java.nio.file.Path;

/**
 * Represents a platform that can offer several services that do not mutate between restarts
 */
public interface Platform { //TODO make typed

    InnerEventBus eventBus();
    ConfigExceptionHandler exceptionHandler();
    Scheduler scheduler();

    PlatformDetail platformDetail();
    Path configPath();
    Colorer colorer();
    UserRegistry<?> userRegistry();

}
