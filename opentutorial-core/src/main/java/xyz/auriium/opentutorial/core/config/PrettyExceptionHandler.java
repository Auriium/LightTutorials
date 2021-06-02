package xyz.auriium.opentutorial.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.arim.dazzleconf.error.InvalidConfigException;
import xyz.auriium.opentutorial.core.UserRegistry;

import java.io.IOException;

public abstract class PrettyExceptionHandler<T> implements ConfigExceptionHandler{

    private final Logger logger = LoggerFactory.getLogger("OpenTutorial");
    private final UserRegistry<T> registry;

    protected PrettyExceptionHandler(UserRegistry<T> registry) {
        this.registry = registry;
    }

    @Override
    public void handle(InvalidConfigException exception) {
        logger.error("An error occurred reading the config! (Config is invalid)", exception);

        relayError("&cAn error occurred while trying to read the configuration for OpenTutorial! Please check console for the full stacktrace and review your configuration!");
    }

    @Override
    public void handle(IOException exception) {
        logger.error("An error occurred reading the config! (Input/Output)", exception);

        relayError("&9OpenTutorial &7> &cAn error occurred while trying to read the configuration for OpenTutorial! A fatal exception occurred, please make an issue on our GitHub!");
    }

    final void relayError(String string) {
        registry.getAllAccessible().forEach(t -> relayErrorMessage(t,string));
    }

    protected abstract void relayErrorMessage(T t, String string);
}
