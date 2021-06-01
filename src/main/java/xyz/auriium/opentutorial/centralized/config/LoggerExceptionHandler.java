package xyz.auriium.opentutorial.centralized.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.arim.dazzleconf.error.InvalidConfigException;

import java.io.IOException;

public class LoggerExceptionHandler implements ConfigExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger("OpenTutorial");

    @Override
    public void handle(InvalidConfigException exception) {
        logger.error("An error occurred reading the config! (Config is invalid)", exception);
    }

    @Override
    public void handle(IOException exception) {
        logger.error("An error occurred reading the config! (Input/Output)", exception);
    }
}
