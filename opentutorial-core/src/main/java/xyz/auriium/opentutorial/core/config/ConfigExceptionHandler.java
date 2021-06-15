package xyz.auriium.opentutorial.core.config;

import space.arim.dazzleconf.error.InvalidConfigException;
import xyz.auriium.opentutorial.core.LoadFailureException;

import java.io.IOException;

public interface ConfigExceptionHandler {

    void signal();

}
