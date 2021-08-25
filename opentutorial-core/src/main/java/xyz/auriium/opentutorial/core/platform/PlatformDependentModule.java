package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.beetle.utility.aspect.UUIDCloseable;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.event.hook.HookRegistry;
import xyz.auriium.opentutorial.core.insertion.InsertionRegistry;
import xyz.auriium.opentutorial.core.platform.impl.CommonDependentModule;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.TemplateController;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.tutorial.CommonTutorialController;
import xyz.auriium.opentutorial.core.tutorial.ReduxTemplateController;

/**
 * Module that describes all baseless (reloadable) constructs available to the plugin
 */
public interface PlatformDependentModule extends UUIDCloseable {

    ConfigController configController();
    ConsumerCentralizer consumerCentralizer();
    TutorialController tutorialController();
    TemplateController templateController();
    InnerEventBus eventBus();

    static PlatformDependentModule load(Platform<?> platform, InsertionRegistry insertionRegistry, HookRegistry hookRegistry) {
        ConfigController configController = ConfigController.load(platform,insertionRegistry);
        ConsumerCentralizer consumerCentralizer = ConsumerCentralizer.load(platform, insertionRegistry, hookRegistry, configController);
        TutorialController tutorialController = new CommonTutorialController(consumerCentralizer, platform.userRegistry());


        InnerEventBus innerEventBus = InnerEventBus.load(platform,hookRegistry,tutorialController,configController);
        TemplateController templateController = ReduxTemplateController.build(configController.getTutorialsConfig());

        return new CommonDependentModule(configController, consumerCentralizer,tutorialController,templateController,innerEventBus);


    }

}
