package xyz.auriium.opentutorial.core;

import xyz.auriium.littlethings.archetypes.closeables.UUIDCloseable;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.event.EventBus;
import xyz.auriium.opentutorial.core.consumer.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.template.TemplateController;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;

import java.util.UUID;

/**
 * Module that describes all baseless (reloadable) constructs available to the plugin
 */
public interface PlatformDependentModule extends UUIDCloseable {

    ConfigController configController();
    ConsumerCentralizer consumerCentralizer();
    TutorialController tutorialController();
    TemplateController templateController();
    EventBus eventBus();


}
