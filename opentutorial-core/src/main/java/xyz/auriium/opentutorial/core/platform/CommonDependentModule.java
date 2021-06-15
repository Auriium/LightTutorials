package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.tutorial.TemplateController;

import java.util.UUID;

public class CommonDependentModule implements PlatformDependentModule {

    private final ConfigController configController;
    private final ConsumerCentralizer consumerCentralizer;
    private final TutorialController tutorialController;
    private final TemplateController templateController;

    public CommonDependentModule(ConfigController configController, ConsumerCentralizer consumerCentralizer, TutorialController tutorialController, TemplateController templateController) {
        this.configController = configController;
        this.consumerCentralizer = consumerCentralizer;
        this.tutorialController = tutorialController;
        this.templateController = templateController;
    }


    @Override
    public ConfigController configController() {
        return configController;
    }

    @Override
    public ConsumerCentralizer consumerCentralizer() {
        return consumerCentralizer;
    }

    @Override
    public TutorialController tutorialController() {
        return tutorialController;
    }

    @Override
    public TemplateController templateController() {
        return templateController;
    }

    @Override
    public void closeSingle(UUID uuid) {
        consumerCentralizer.closeSingle(uuid);
    }

    @Override
    public void close() {
        consumerCentralizer.close();
    }
}
