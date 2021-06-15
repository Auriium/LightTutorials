package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.platform.base.Colorer;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.UserRegistry;

import java.nio.file.Path;

public interface Platform { //TODO make typed

    InnerEventBus eventBus();
    ConfigExceptionHandler exceptionHandler();
    Scheduler scheduler();

    Path configPath();
    Colorer colorer();
    UserRegistry<?> userRegistry();

}
