package xyz.auriium.opentutorial.core;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.consumer.CommonConsumerCentralizer;
import xyz.auriium.opentutorial.core.consumer.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.event.EventBus;
import xyz.auriium.opentutorial.core.event.ReduxEventBus;
import xyz.auriium.opentutorial.core.template.CommonTemplateController;
import xyz.auriium.opentutorial.core.template.TemplateController;
import xyz.auriium.opentutorial.core.tutorial.CancellingConduit;
import xyz.auriium.opentutorial.core.tutorial.CommonTutorialController;
import xyz.auriium.opentutorial.core.tutorial.ConsumerFailureConduit;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;

import java.util.UUID;

public class CommonDependentModule implements PlatformDependentModule {

    private final InternalDependentModule module;
    private final ConsumerCentralizer consumerCentralizer;
    private final TutorialController tutorialController;
    private final TemplateController templateController;
    private final EventBus outerEventBus;

    CommonDependentModule(InternalDependentModule module, ConsumerCentralizer consumerCentralizer, TutorialController tutorialController, TemplateController templateController, EventBus outerEventBus) {
        this.module = module;
        this.consumerCentralizer = consumerCentralizer;
        this.tutorialController = tutorialController;
        this.templateController = templateController;
        this.outerEventBus = outerEventBus;
    }


    @Override
    public ConfigController configController() {
        return module.configController();
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
    public EventBus eventBus() {
        return outerEventBus;
    }

    @Override
    public void close() {
        tutorialController.close();
    }

    @Override
    public void closeSingle(UUID uuid) {
        tutorialController.closeSingle(uuid);
    }

    //active pattern launcher
    public static CommonDependentModule launch(Platform platform, ConsumerRegistry registry) {

        ConsumerFailureConduit consumerFailureConduit = CancellingConduit.launch(platform);
        InternalDependentModule module = CommonInternalModule.launch(platform, registry);
        ConsumerCentralizer consumerCentralizer = new CommonConsumerCentralizer(registry, consumerFailureConduit);
        TutorialController tutorialController = new CommonTutorialController(platform, module, consumerCentralizer);
        TemplateController templateController = CommonTemplateController.build(module.configController().getTutorialsConfig());
        EventBus eventBus = new ReduxEventBus(registry, tutorialController, consumerFailureConduit);

        return new CommonDependentModule(module, consumerCentralizer, tutorialController, templateController, eventBus);
    }

}
