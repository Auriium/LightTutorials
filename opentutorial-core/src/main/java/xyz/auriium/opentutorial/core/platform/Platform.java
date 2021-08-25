package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.tutorial.stage.Suppressor;
import xyz.auriium.opentutorial.core.tutorial.stage.Lockable;

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
