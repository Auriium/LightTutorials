package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.beetle.utility.aspect.UUIDCloseable;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.templates.SerializerRegistry;
import xyz.auriium.opentutorial.core.event.CommonInnerEventBus;
import xyz.auriium.opentutorial.core.event.CommonOuterEventBus;
import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.event.OuterEventBus;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;
import xyz.auriium.opentutorial.core.tutorial.TemplateController;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.tutorial.impl.CommonTemplateController;
import xyz.auriium.opentutorial.core.tutorial.impl.CommonTutorialController;

public interface PlatformDependentModule extends UUIDCloseable {

    ConfigController configController();
    ConsumerCentralizer consumerCentralizer();
    TutorialController tutorialController();
    TemplateController templateController();
    OuterEventBus eventBus();

    static PlatformDependentModule load(Platform platform, SerializerRegistry serializerRegistry, ConsumerRegistry consumerRegistry) {
        ConfigController configController = ConfigController.load(platform,serializerRegistry);
        ConsumerCentralizer consumerCentralizer = ConsumerCentralizer.load(platform,consumerRegistry,configController);
        TutorialController tutorialController = new CommonTutorialController(consumerCentralizer);
        OuterEventBus outerEventBus = new CommonOuterEventBus(tutorialController,platform.eventBus());


        TemplateController templateController = new CommonTemplateController(configController.getTutorialsConfig());

        return new CommonDependentModule(configController, consumerCentralizer,tutorialController,templateController);
    }

}
