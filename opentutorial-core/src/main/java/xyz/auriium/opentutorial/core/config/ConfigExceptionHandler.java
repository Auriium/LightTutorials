package xyz.auriium.opentutorial.core.config;

import space.arim.dazzleconf.error.InvalidConfigException;

import java.io.IOException;

public interface ConfigExceptionHandler {

    void handle(InvalidConfigException exception);
    void handle(IOException exception);

}
