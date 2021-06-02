package xyz.auriium.opentutorial.core.config;

import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.InvalidConfigException;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.helper.ConfigurationHelper;

import java.io.IOException;
import java.nio.file.Path;

public class ReloadableHelper<T> {

    private final ConfigurationHelper<T> helper;
    private final ConfigExceptionHandler handler;

    private T nullable;

    public ReloadableHelper(Class<T> clazz, Path path, String fileName, ConfigExceptionHandler handler, ConfigurationOptions options) {
        this.helper = new ConfigurationHelper<>(path,fileName, SnakeYamlConfigurationFactory.create(clazz,options));
        this.handler = handler;
    }


    /**
     * Returns the config as a raw value that can be null if the config is not initialized
     * @return the config
     */
    public T getConfig() {
        return nullable;
    }

    /**
     * Reloads the configuration or loads it. Must be called once before the config returned by {@link}
     * is not null. Delegates invalid config exceptions to a handler to allow pretty exception printing or slf logging
     */
    public void reload() {

        try {
            this.nullable = helper.reloadConfigData();
        } catch (IOException | InvalidConfigException e) {
            e.printStackTrace();
        }

    }

}
