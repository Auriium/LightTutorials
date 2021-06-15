package xyz.auriium.opentutorial.core.platform.impl;

import xyz.auriium.beetle.utility.aspect.UUIDCloseable;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.SerializerRegistry;
import xyz.auriium.opentutorial.core.event.CommonOuterEventBus;
import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.event.OuterEventBus;
import xyz.auriium.opentutorial.core.event.hook.HookRegistry;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;
import xyz.auriium.opentutorial.core.tutorial.TemplateController;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.tutorial.impl.CommonTemplateController;
import xyz.auriium.opentutorial.core.tutorial.impl.CommonTutorialController;

interface PlatformDependentModule extends UUIDCloseable {

    ConfigController configController();
    ConsumerCentralizer consumerCentralizer();
    TutorialController tutorialController();
    TemplateController templateController();
    InnerEventBus eventBus();

    static PlatformDependentModule load(Platform platform, SerializerRegistry serializerRegistry, ConsumerRegistry consumerRegistry, HookRegistry hookRegistry) {
        ConfigController configController = ConfigController.load(platform,serializerRegistry);
        ConsumerCentralizer consumerCentralizer = ConsumerCentralizer.load(platform,consumerRegistry,hookRegistry,configController);
        TutorialController tutorialController = new CommonTutorialController(consumerCentralizer);


        InnerEventBus innerEventBus = InnerEventBus.load(platform,hookRegistry,tutorialController,configController);
        TemplateController templateController = new CommonTemplateController(configController.getTutorialsConfig());

        return new CommonDependentModule(configController, consumerCentralizer,tutorialController,templateController,innerEventBus);
    }

}
