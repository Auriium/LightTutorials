package me.aurium.opentutorial;

import me.aurium.opentutorial.centralized.CommonTutorialController;
import me.aurium.opentutorial.centralized.TutorialController;
import me.aurium.opentutorial.centralized.registry.CommonEventBus;
import me.aurium.opentutorial.centralized.registry.CommonRegistry;
import me.aurium.opentutorial.centralized.registry.ConsumerRegistry;
import me.aurium.opentutorial.centralized.registry.EventBus;
import me.aurium.opentutorial.centralized.template.TemplateController;
import me.aurium.opentutorial.hook.EventBusHook;
import org.bukkit.plugin.java.JavaPlugin;

public class OpenTutorial extends JavaPlugin {

    private final EventBus bus = new CommonEventBus();
    private final ConsumerRegistry registry = new CommonRegistry(bus);

    private final TemplateController templateController = new TemplateController();
    private final TutorialController tutorialController = new CommonTutorialController(registry);

    private final EventBusHook hook = new EventBusHook(bus);

    @Override
    public void onEnable() {

    }
}
