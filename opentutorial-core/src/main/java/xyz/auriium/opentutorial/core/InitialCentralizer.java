package xyz.auriium.opentutorial.core;

import xyz.auriium.opentutorial.core.model.Cycleable;
import xyz.auriium.opentutorial.core.config.ConfigCentralizer;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;

public class InitialCentralizer implements Cycleable {

    private final ConfigCentralizer configCentralizer;
    private final ConsumerRegistry consumerRegistry;
    private final TutorialController tutorialController;

    public InitialCentralizer(ConfigCentralizer configCentralizer, ConsumerRegistry consumerRegistry, TutorialController tutorialController) {
        this.configCentralizer = configCentralizer;
        this.consumerRegistry = consumerRegistry;
        this.tutorialController = tutorialController;
    }

    @Override
    public void startup() {
        configCentralizer.startup();
        consumerRegistry.startup();
        tutorialController.startup();
    }

    @Override
    public void reload() {
        configCentralizer.reload();
        consumerRegistry.reload();
        tutorialController.reload();
    }

    @Override
    public void shutdown() {
        configCentralizer.shutdown();
        consumerRegistry.shutdown();
        tutorialController.shutdown();
    }
}
