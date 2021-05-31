package me.aurium.opentutorial;

import co.aikar.commands.BukkitCommandManager;
import me.aurium.opentutorial.centralized.CommonTutorialController;
import me.aurium.opentutorial.centralized.TutorialController;
import me.aurium.opentutorial.centralized.config.GeneralConfig;
import me.aurium.opentutorial.centralized.config.ReloadableHelper;
import me.aurium.opentutorial.centralized.registry.CommonEventBus;
import me.aurium.opentutorial.centralized.registry.CommonRegistry;
import me.aurium.opentutorial.centralized.registry.EventBus;
import me.aurium.opentutorial.centralized.server.UUIDRegistry;
import me.aurium.opentutorial.centralized.server.WrappedUUIDRegistry;
import me.aurium.opentutorial.centralized.template.TemplateController;
import me.aurium.opentutorial.command.TutorialCommand;
import me.aurium.opentutorial.hook.EventBusHook;
import me.aurium.opentutorial.hook.LockHook;
import me.aurium.opentutorial.hook.StartupHook;
import me.aurium.opentutorial.stage.action.ChatStageConsumer;
import me.aurium.opentutorial.stage.action.ChatStageSerializer;
import me.aurium.opentutorial.stage.action.CommandStageConsumer;
import me.aurium.opentutorial.stage.action.CommandStageSerializer;
import me.aurium.opentutorial.stage.delay.DelaySerializer;
import me.aurium.opentutorial.stage.delay.DelayStageConsumer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
            .register(new DelayStageConsumer(this), new DelaySerializer())

    );

    private final LockHook lockHook = new LockHook();
    private final EventBusHook eventHook = new EventBusHook(bus, this, tutorialController);
    private final StartupHook startupHook = new StartupHook(tutorialController, templateController, null);

    private final BukkitCommandManager commandManager = new BukkitCommandManager(this);

    @Override
    public void onEnable() {
        PluginManager manager = getServer().getPluginManager();

        manager.registerEvents(eventHook,this);
        manager.registerEvents(startupHook,this);
        manager.registerEvents(lockHook,this);

        commandManager.registerCommand(new TutorialCommand(tutorialController,templateController));

    }
}
