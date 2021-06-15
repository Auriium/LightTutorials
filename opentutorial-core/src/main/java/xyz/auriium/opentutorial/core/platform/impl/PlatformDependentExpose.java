package xyz.auriium.opentutorial.core.platform.impl;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.TemplateController;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;

public interface PlatformDependentExpose {

    ConfigController configController();
    ConsumerCentralizer consumerCentralizer();
    TutorialController tutorialController();
    TemplateController templateController();


}
