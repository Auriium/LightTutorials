package xyz.auriium.opentutorial.core.config;

import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.InvalidConfigException;
import xyz.auriium.opentutorial.core.LoadFailureException;
import xyz.auriium.opentutorial.core.config.impl.CommonConfigController;
import xyz.auriium.opentutorial.core.config.impl.ResourceHelper;
import xyz.auriium.opentutorial.core.config.impl.WrappingHelper;
import xyz.auriium.opentutorial.core.config.messages.MessageConfSerializer;
import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.config.templates.TutorialsConfig;
import xyz.auriium.opentutorial.core.config.templates.util.StageConfSerializer;
import xyz.auriium.opentutorial.core.insertion.InsertionRegistry;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.platform.base.Colorer;

import java.io.IOException;
import java.nio.file.Path;

public interface ConfigController {

    //i hate working with null bullshit
    MessageConfig getMessageConfig();
    GeneralConfig getGeneralConfig();
    TutorialsConfig getTutorialsConfig();

    static ConfigController load(Platform<?> platform, InsertionRegistry registry) {
        Path directory = platform.configPath();
        Colorer colorer = platform.colorer();

        try {
            MessageConfig config = new WrappingHelper<>(
                    MessageConfig.class,
                    directory,
                    "messages.yml",
                    new ConfigurationOptions.Builder().addSerialiser(new MessageConfSerializer(colorer)).build()
            ).load();


            GeneralConfig generalConfig = new WrappingHelper<>(
                    GeneralConfig.class,
                    directory,
                    "general.yml",
                    ConfigurationOptions.defaults()
            ).load();

            TutorialsConfig tutorialsConfig = new ResourceHelper<>(
                    TutorialsConfig.class,
                    directory,
                    "tutorials.yml",
                    new ConfigurationOptions.Builder().addSerialiser(new StageConfSerializer(registry)).build()
            ).load();

            return new CommonConfigController(config, generalConfig, tutorialsConfig);
        } catch (IOException exception) {
            platform.exceptionHandler().signal(exception);
            throw new LoadFailureException(exception);
        } catch (InvalidConfigException exception) {
            platform.exceptionHandler().signal(exception);
            throw new LoadFailureException(exception);
        }
    }

}
