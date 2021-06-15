package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.event.EventBus;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.platform.base.Colorer;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.UserRegistry;

import java.nio.file.Path;

public interface Platform {

    ConfigExceptionHandler exceptionHandler();
    EventBus eventBus();
    Scheduler scheduler();

    Path configPath();
    Colorer colorer();
    UserRegistry<?> userRegistry();

}
