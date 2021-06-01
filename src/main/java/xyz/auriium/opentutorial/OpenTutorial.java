package xyz.auriium.opentutorial;

import co.aikar.commands.BukkitCommandManager;
import space.arim.dazzleconf.ConfigurationOptions;
import xyz.auriium.opentutorial.centralized.CommonTutorialController;
import xyz.auriium.opentutorial.centralized.TutorialController;
import xyz.auriium.opentutorial.centralized.config.ConfGeneral;
import xyz.auriium.opentutorial.centralized.config.ReloadableHelper;
import xyz.auriium.opentutorial.centralized.registry.CommonEventBus;
import xyz.auriium.opentutorial.centralized.registry.CommonRegistry;
import xyz.auriium.opentutorial.centralized.registry.EventBus;
import xyz.auriium.opentutorial.centralized.server.UUIDRegistry;
import xyz.auriium.opentutorial.centralized.server.WrappedUUIDRegistry;
import xyz.auriium.opentutorial.centralized.template.TemplateController;
import xyz.auriium.opentutorial.command.TutorialCommand;
import xyz.auriium.opentutorial.hook.EventBusHook;
import xyz.auriium.opentutorial.hook.LockHook;
import xyz.auriium.opentutorial.hook.StartupHook;
import xyz.auriium.opentutorial.stage.action.ChatStageConsumer;
import xyz.auriium.opentutorial.stage.action.ChatStageSerializer;
import xyz.auriium.opentutorial.stage.action.CommandStageConsumer;
import xyz.auriium.opentutorial.stage.action.CommandStageSerializer;
import xyz.auriium.opentutorial.stage.delay.DelaySerializer;
import xyz.auriium.opentutorial.stage.delay.DelayStageConsumer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OpenTutorial extends JavaPlugin {

    //Base level dependencies

    private final ReloadableHelper<ConfGeneral> generalConfig =
            new ReloadableHelper<>(
                    ConfGeneral.class,getDataFolder().toPath(),
                    "general.yml",
                    null,
                    ConfigurationOptions.defaults()
            );

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
