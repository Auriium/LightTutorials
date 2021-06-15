package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.event.OuterEventBus;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.TemplateController;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;

public interface PlatformDependentModuleExpose {

    ConfigController configController();
    ConsumerCentralizer consumerCentralizer();
    TutorialController tutorialController();
    TemplateController templateController();
    OuterEventBus eventBus();

}
