package xyz.auriium.opentutorial.core.config;

import space.arim.dazzleconf.ConfigurationOptions;
import xyz.auriium.opentutorial.core.config.types.tutorials.StageConfSerializer;
import xyz.auriium.opentutorial.core.config.types.tutorials.TutorialsConfig;
import xyz.auriium.opentutorial.core.model.Cycleable;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;

import java.nio.file.Path;

public class TutorialCentralizer implements Cycleable {

    private final ResourceHelper<TutorialsConfig> tutorialsConfig;

    public TutorialCentralizer(ConsumerRegistry registry, Path directory, ConfigExceptionHandler handler) {
        this.tutorialsConfig = new ResourceHelper<>(
                TutorialsConfig.class,
                directory,
                "tutorials.yml",
                handler,
                new ConfigurationOptions.Builder().addSerialiser(new StageConfSerializer(registry)).build()
        );
    }

    public ResourceHelper<TutorialsConfig> getTutorialsConfig() {
        return tutorialsConfig;
    }

    @Override
    public void startup() {
        tutorialsConfig.reload();
    }

    @Override
    public void reload() {
        tutorialsConfig.reload();
    }

    @Override
    public void shutdown() {

    }
}
