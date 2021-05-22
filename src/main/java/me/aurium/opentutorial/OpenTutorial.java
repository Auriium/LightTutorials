package me.aurium.opentutorial;

import me.aurium.opentutorial.centralized.CommonTutorialController;
import me.aurium.opentutorial.centralized.TutorialController;
import me.aurium.opentutorial.centralized.registry.CommonEventBus;
import me.aurium.opentutorial.centralized.registry.CommonRegistry;
import me.aurium.opentutorial.centralized.registry.ConsumerRegistry;
import me.aurium.opentutorial.centralized.registry.EventBus;
import me.aurium.opentutorial.centralized.template.TemplateController;
import me.aurium.opentutorial.hook.EventBusHook;
import me.aurium.opentutorial.hook.StartupHook;
import org.bukkit.plugin.java.JavaPlugin;

public class OpenTutorial extends JavaPlugin {

    private final EventBus bus = new CommonEventBus();
    private final ConsumerRegistry registry = new CommonRegistry(bus);

    //TODO template loading - registry must have all things registered beforehand
    private final TemplateController templateController = new TemplateController();
    private final TutorialController tutorialController = new CommonTutorialController(registry);

    private final EventBusHook hook = new EventBusHook(bus);
    private final StartupHook startupHook = new StartupHook(tutorialController, templateController, null);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(hook,this);
        getServer().getPluginManager().registerEvents(startupHook,this);
    }
}
