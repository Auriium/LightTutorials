package xyz.auriium.opentutorial.core.config.impl;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.arim.dazzleconf.error.InvalidConfigException;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.model.AudienceRegistry;


import java.io.IOException;


public class PrettyExceptionHandler implements ConfigExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger("OpenTutorial");

    private final AudienceRegistry registry;

    public PrettyExceptionHandler(AudienceRegistry registry) {
        this.registry = registry;
    }


    @Override
    public void handle(InvalidConfigException exception) {
        logger.error("An error occurred reading the config! (Config is invalid)", exception);
        logger.error("Please repair your config and restart the server!");

        relayError("&cAn error occurred while trying to read the configuration for OpenTutorial! Please check console for the full stacktrace and review your configuration!");
    }

    @Override
    public void handle(IOException exception) {
        logger.error("An error occurred reading the config! (Input/Output)", exception);
        logger.error("Please contact the developer, an irrepairable error has occured during execution!");

        relayError("&9OpenTutorial &7> &cAn error occurred while trying to read the configuration for OpenTutorial! A fatal exception occurred, please make an issue on our GitHub!");
    }

    final void relayError(String string) {
        registry.getAllAccessibleAudiences().forEach(audience -> audience.sendMessage(string));
    }


}
