package xyz.auriium.opentutorial.core.platform.impl;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.TemplateController;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;

public class CommonPlatformDependentExpose implements PlatformDependentExpose {

    private final PlatformDependentLoader centralizer;

    public CommonPlatformDependentExpose(PlatformDependentLoader centralizer) {
        this.centralizer = centralizer;
    }

    @Override
    public ConfigController configController() {
        return centralizer.getModule().configController();
    }

    @Override
    public ConsumerCentralizer consumerCentralizer() {
        return centralizer.getModule().consumerCentralizer();
    }

    @Override
    public TutorialController tutorialController() {
        return centralizer.getModule().tutorialController();
    }

    @Override
    public TemplateController templateController() {
        return centralizer.getModule().templateController();
    }


}
