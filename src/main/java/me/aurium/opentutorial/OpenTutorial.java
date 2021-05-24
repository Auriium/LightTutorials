package me.aurium.opentutorial;

import me.aurium.opentutorial.centralized.CommonTutorialController;
import me.aurium.opentutorial.centralized.TutorialController;
import me.aurium.opentutorial.centralized.config.GeneralConfig;
import me.aurium.opentutorial.centralized.config.ReloadableHelper;
import me.aurium.opentutorial.centralized.registry.CommonEventBus;
import me.aurium.opentutorial.centralized.registry.CommonRegistry;
import me.aurium.opentutorial.centralized.registry.EventBus;
import me.aurium.opentutorial.centralized.server.UUIDRegistry;
import me.aurium.opentutorial.centralized.server.WrappedUUIDRegistry;
import me.aurium.opentutorial.centralized.states.StateMap;
import me.aurium.opentutorial.centralized.template.TemplateController;
import me.aurium.opentutorial.hook.EventBusHook;
import me.aurium.opentutorial.hook.StartupHook;
import me.aurium.opentutorial.stage.action.ChatStageConsumer;
import me.aurium.opentutorial.stage.action.ChatStageSerializer;
import me.aurium.opentutorial.stage.action.CommandStageConsumer;
import me.aurium.opentutorial.stage.action.CommandStageSerializer;
import me.aurium.opentutorial.stage.delay.DelaySerializer;
import me.aurium.opentutorial.stage.delay.DelayStageConsumer;
import org.bukkit.plugin.java.JavaPlugin;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.helper.ConfigurationHelper;

public class OpenTutorial extends JavaPlugin {

    //Base level dependencies

    private final ReloadableHelper<GeneralConfig> generalConfig =
            new ReloadableHelper<>(GeneralConfig.class,getDataFolder().toPath(),"general.yml",null,null);

    private final PluginScheduler scheduler = new PluginScheduler(this);
    private final UUIDRegistry uuidRegistry = new WrappedUUIDRegistry(this);
    private final EventBus bus = new CommonEventBus();

    //TODO template loading - registry must have all things registered beforehand
    private final TemplateController templateController = new TemplateController();
    private final TutorialController tutorialController = new CommonTutorialController(

            new CommonRegistry(bus)
            .register(new ChatStageConsumer(this), new ChatStageSerializer())
            .register(new CommandStageConsumer(this), new CommandStageSerializer())
            .register(new DelayStageConsumer(this), new DelaySerializer()),

            new StateMap(uuidRegistry,scheduler)

    );

    private final EventBusHook hook = new EventBusHook(bus);
    private final StartupHook startupHook = new StartupHook(tutorialController, templateController, null);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(hook,this);
        getServer().getPluginManager().registerEvents(startupHook,this);
    }
}
