package xyz.auriium.opentutorial.core.platform.impl;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.event.OuterEventBus;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.tutorial.TemplateController;

import java.util.UUID;

public class CommonDependentModule implements PlatformDependentModule {

    private final ConfigController configController;
    private final ConsumerCentralizer consumerCentralizer;
    private final TutorialController tutorialController;
    private final TemplateController templateController;
    private final OuterEventBus outerEventBus;

    public CommonDependentModule(ConfigController configController, ConsumerCentralizer consumerCentralizer, TutorialController tutorialController, TemplateController templateController, OuterEventBus outerEventBus) {
        this.configController = configController;
        this.consumerCentralizer = consumerCentralizer;
        this.tutorialController = tutorialController;
        this.templateController = templateController;
        this.outerEventBus = outerEventBus;
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
    public OuterEventBus eventBus() {
        return outerEventBus;
    }

    @Override
    public void closeSingle(UUID uuid) {
//TODO impl
    }

    @Override
    public void close() {

    }
}
