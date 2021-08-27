package xyz.auriium.opentutorial.core.config;

import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.InvalidConfigException;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.opentutorial.core.ConsumerRegistry;
import xyz.auriium.opentutorial.core.LoadFailureException;
import xyz.auriium.opentutorial.core.MissingServiceSupplier;
import xyz.auriium.opentutorial.core.config.templates.StageConfSerializer;
import xyz.auriium.opentutorial.core.config.templates.TutorialsConfig;
import xyz.auriium.opentutorial.core.platform.MessagingExceptionHandler;

import java.io.IOException;
import java.nio.file.Path;

public class CommonConfigController implements ConfigController {

    private final MessageConfig messageConfig;
    private final GeneralConfig generalConfig;
    private final TutorialsConfig tutorialsConfig;

    public CommonConfigController(MessageConfig messageConfig, GeneralConfig generalConfig, TutorialsConfig tutorialsConfig) {
        this.messageConfig = messageConfig;
        this.generalConfig = generalConfig;
        this.tutorialsConfig = tutorialsConfig;
    }

    @Override
    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    @Override
    public GeneralConfig getGeneralConfig() {
        return generalConfig;
    }

    @Override
    public TutorialsConfig getTutorialsConfig() {
        return tutorialsConfig;
    }

    //active pattern (launcher) to actively create a Platform object
    public static CommonConfigController launch(Platform platform, ConsumerRegistry registry) {
        Path directory = platform.getLocation().getFolderPath();
        Colorer colorer = platform.colorer();
        MessagingExceptionHandler handler = platform.serviceRegistry().retrieve(MessagingExceptionHandler.class).orElseThrow(new MissingServiceSupplier("ConfigExceptionHandler"));

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
            handler.failIO(exception);

            throw new LoadFailureException(exception);
        } catch (InvalidConfigException exception) {
            handler.failConfig(exception);

            throw new LoadFailureException(exception);
        }
    }
}
