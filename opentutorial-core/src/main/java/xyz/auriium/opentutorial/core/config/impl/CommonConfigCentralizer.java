package xyz.auriium.opentutorial.core.config.impl;

import space.arim.dazzleconf.ConfigurationOptions;
import xyz.auriium.opentutorial.core.config.*;
import xyz.auriium.opentutorial.core.config.GeneralConfig;
import xyz.auriium.opentutorial.core.config.messages.MessageConfSerializer;
import xyz.auriium.opentutorial.core.config.templates.impl.StageConfSerializer;
import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.config.templates.TutorialsConfig;
import xyz.auriium.opentutorial.core.model.Colorer;

import java.nio.file.Path;

public class CommonConfigCentralizer implements ConfigCentralizer {

    private final ReloadableHelper<MessageConfig> messageConfig;
    private final ReloadableHelper<GeneralConfig> generalConfig;
    private final ResourceHelper<TutorialsConfig> tutorialsConfig;

    public CommonConfigCentralizer(ConfigExceptionHandler handler, Colorer colorer, Path directory) {
        this.messageConfig = new ReloadableHelper<>(
                MessageConfig.class,
                directory,
                "messages.yml",
                handler,
                new ConfigurationOptions.Builder().addSerialiser(new MessageConfSerializer(colorer)).build()
        );


        this.generalConfig = new ReloadableHelper<>(
                GeneralConfig.class,
                directory,
                "general.yml",
                handler,
                ConfigurationOptions.defaults()
        );

        this.tutorialsConfig = new ResourceHelper<>(
                TutorialsConfig.class,
                directory,
                "tutorials.yml",
                handler,
                new ConfigurationOptions.Builder().addSerialiser(new StageConfSerializer(null)).build()
        );
    }

    @Override
    public ConfigHolder<MessageConfig> getMessageConfig() {
        return messageConfig;
    }

    @Override
    public ConfigHolder<GeneralConfig> getGeneralConfig() {
        return generalConfig;
    }

    @Override
    public ConfigHolder<TutorialsConfig> getTutorialsConfig() {
        return tutorialsConfig;
    }

    @Override
    public void startup() {
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
