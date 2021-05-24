package me.aurium.opentutorial.centralized.config;

import space.arim.dazzleconf.error.InvalidConfigException;

public interface ConfigExceptionHandler {

    void handle(InvalidConfigException exception);

}
