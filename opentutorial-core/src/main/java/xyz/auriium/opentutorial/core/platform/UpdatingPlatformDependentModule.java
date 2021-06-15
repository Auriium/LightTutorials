package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.event.OuterEventBus;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.TemplateController;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;

import java.util.UUID;

public class UpdatingPlatformDependentModule implements PlatformDependentModuleExpose {

    private final PluginCentralizer centralizer;

    public UpdatingPlatformDependentModule(PluginCentralizer centralizer) {
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

    @Override
    public OuterEventBus eventBus() {
        return centralizer.getModule().eventBus();
    }

}
