package xyz.auriium.opentutorial.core.config.impl;

import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.InvalidConfigException;
import space.arim.dazzleconf.ext.snakeyaml.CommentMode;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions;
import space.arim.dazzleconf.helper.ConfigurationHelper;
import xyz.auriium.opentutorial.core.LoadFailureException;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;

import java.io.IOException;
import java.nio.file.Path;

public class WrappingHelper<T> {

    private final ConfigurationHelper<T> helper;

    public WrappingHelper(Class<T> clazz, Path path, String fileName, ConfigurationOptions options) {
        this.helper = new ConfigurationHelper<>(path,fileName, SnakeYamlConfigurationFactory.create(clazz,options,new SnakeYamlOptions.Builder()
                .commentMode(CommentMode.alternativeWriter()) // Enables writing YAML comments
                .build()));
    }

    public T load() throws IOException, InvalidConfigException {
        return helper.reloadConfigData();
    }

}
