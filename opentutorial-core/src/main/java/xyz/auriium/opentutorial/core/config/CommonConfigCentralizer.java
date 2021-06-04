package xyz.auriium.opentutorial.core.config;

import space.arim.dazzleconf.ConfigurationOptions;
import xyz.auriium.opentutorial.core.config.types.general.GeneralConfig;
import xyz.auriium.opentutorial.core.config.types.messages.MessageConfSerializer;
import xyz.auriium.opentutorial.core.config.types.messages.MessageConfig;
import xyz.auriium.opentutorial.core.config.types.tutorials.StageConfSerializer;
import xyz.auriium.opentutorial.core.config.types.tutorials.TutorialsConfig;
import xyz.auriium.opentutorial.core.model.Colorer;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;

import java.nio.file.Path;

public class CommonConfigCentralizer implements ConfigCentralizer {

    private final ReloadableHelper<MessageConfig> messageConfig;
    private final ReloadableHelper<TutorialsConfig> tutorialsConfig;
    private final ReloadableHelper<GeneralConfig> generalConfig;

    public CommonConfigCentralizer(ConfigExceptionHandler handler, ConsumerRegistry registry, Colorer colorer, Path directory) {
        this.messageConfig = new ReloadableHelper<>(
                MessageConfig.class,
                directory,
                "messages.yml",
                handler,
                new ConfigurationOptions.Builder().addSerialiser(new MessageConfSerializer(colorer)).build()
        );

        this.tutorialsConfig = new ReloadableHelper<>(
                TutorialsConfig.class,
                directory,
                "tutorials.yml",
                handler,
                new ConfigurationOptions.Builder().addSerialiser(new StageConfSerializer(registry)).build()
        );

        this.generalConfig = new ReloadableHelper<>(
                GeneralConfig.class,
                directory,
                "general.yml",
                handler,
                ConfigurationOptions.defaults()
        );
    }




    @Override
    public MessageConfig getMessageConfig() {
        return messageConfig.getConfig();
    }

    @Override
    public TutorialsConfig getTutorialsConfig() {
        return tutorialsConfig.getConfig();
    }

    @Override
    public GeneralConfig getGeneralConfig() {
        return generalConfig.getConfig();
    }

    @Override
    public void startup() {
        tutorialsConfig.reload();
        messageConfig.reload();
        generalConfig.reload();
    }

    @Override
    public void reload() {
        this.startup();
    }

    @Override
    public void shutdown() {
        //no-ops
    }
}
