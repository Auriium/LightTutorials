package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.platform.base.Colorer;
import xyz.auriium.opentutorial.core.platform.base.PlatformDetail;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.UserRegistry;
import xyz.auriium.opentutorial.core.stage.chat.Suppressor;
import xyz.auriium.opentutorial.core.stage.lock.Lockable;

import java.nio.file.Path;

/**
 * Represents a platform that can offer several services that do not mutate between restarts
 */
public interface Platform<T> {

    ConfigExceptionHandler exceptionHandler();
    Scheduler scheduler();

    PlatformDetail platformDetail();
    Path configPath();
    Colorer colorer();
    UserRegistry<T> userRegistry();

    Lockable lockable();
    Suppressor suppressor();

}
