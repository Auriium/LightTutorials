package xyz.auriium.opentutorial.core.config;

import space.arim.dazzleconf.error.InvalidConfigException;

import java.io.IOException;

public interface ConfigExceptionHandler {

    void signal(IOException exception);
    void signal(InvalidConfigException exception);

}
