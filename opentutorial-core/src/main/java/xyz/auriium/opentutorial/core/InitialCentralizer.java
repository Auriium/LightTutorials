package xyz.auriium.opentutorial.core;

import xyz.auriium.opentutorial.core.model.Cycleable;
import xyz.auriium.opentutorial.core.config.ConfigCentralizer;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;

public class InitialCentralizer implements Cycleable {

    private final ConfigCentralizer configCentralizer;
    private final ConsumerRegistry consumerRegistry;

    public InitialCentralizer(ConfigCentralizer configCentralizer, ConsumerRegistry consumerRegistry) {
        this.configCentralizer = configCentralizer;
        this.consumerRegistry = consumerRegistry;
    }

    @Override
    public void startup() {
        configCentralizer.startup();
        consumerRegistry.startup();
    }

    @Override
    public void reload() {
        configCentralizer.reload();
        consumerRegistry.reload();
    }

    @Override
    public void shutdown() {
        configCentralizer.shutdown();
        consumerRegistry.shutdown();
    }
}
