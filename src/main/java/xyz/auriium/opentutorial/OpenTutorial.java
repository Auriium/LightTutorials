package xyz.auriium.opentutorial;

import co.aikar.commands.BukkitCommandManager;
import space.arim.dazzleconf.ConfigurationOptions;
import xyz.auriium.opentutorial.centralized.CommonTutorialController;
import xyz.auriium.opentutorial.centralized.config.ConfGeneral;
import xyz.auriium.opentutorial.centralized.config.PrettyExceptionHandler;
import xyz.auriium.opentutorial.centralized.config.ReloadableHelper;
import xyz.auriium.opentutorial.core.centralized.config.types.messages.ConfMessages;
import xyz.auriium.opentutorial.core.centralized.config.types.messages.MessageConfSerializer;
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
import xyz.auriium.opentutorial.stage.response.AgeStageConsumer;
import xyz.auriium.opentutorial.stage.response.AgeStageSerializer;
import xyz.auriium.opentutorial.stage.response.PlainKeywordSerializer;
import xyz.auriium.opentutorial.stage.response.PlainKeywordStageConsumer;
import xyz.auriium.opentutorial.stage.state.InvisibleStageConsumer;
import xyz.auriium.opentutorial.stage.state.InvisibleStageSerializer;
import xyz.auriium.opentutorial.stage.state.LockStageConsumer;
import xyz.auriium.opentutorial.stage.state.LockStageSerializer;

public class OpenTutorial extends JavaPlugin {

    //Base level dependencies

    private final PrettyExceptionHandler prettyExceptionHandler = new PrettyExceptionHandler(this);



    private final ReloadableHelper<ConfGeneral> generalConfig =
            new ReloadableHelper<>(
                    ConfGeneral.class,getDataFolder().toPath(),
                    "general.yml",
                    prettyExceptionHandler,
                    ConfigurationOptions.defaults()
            );

    private final ReloadableHelper<ConfMessages> messageConfig =
            new ReloadableHelper<>(
                    ConfMessages.class,getDataFolder().toPath(),
                    "messages.yml",
                    prettyExceptionHandler,
                    new ConfigurationOptions.Builder().addSerialiser(new MessageConfSerializer()).build()
            );


    private final PluginScheduler pluginScheduler = new PluginScheduler(this);
    private final UUIDRegistry uuidRegistry = new WrappedUUIDRegistry(this);
    private final PluginCommand pluginDispatcher = new PluginCommand(this);
    private final EventBus bus = new CommonEventBus();

    private final LockHook lockHook = new LockHook();

    private final ConsumerRegistry registry = new CommonRegistry(bus)
            //action
            .register(new ChatStageConsumer(uuidRegistry), new ChatStageSerializer())
            .register(new CommandStageConsumer(uuidRegistry,pluginDispatcher), new CommandStageSerializer())
            //delay
            .register(new DelayStageConsumer(pluginScheduler), new DelaySerializer())
            //response
            .register(new AgeStageConsumer(pluginScheduler, uuidRegistry, pluginDispatcher), new AgeStageSerializer())
            .register(new PlainKeywordStageConsumer(pluginScheduler), new PlainKeywordSerializer())
            //state
            .register(new InvisibleStageConsumer(uuidRegistry),new InvisibleStageSerializer())
            .register(new LockStageConsumer(lockHook), new LockStageSerializer());

    private final TemplateController templateController = new TemplateController (
            getDataFolder().toPath(),
            "tutorials.yml",
            prettyExceptionHandler,
            registry
    );

    private final TutorialController tutorialController = new CommonTutorialController(registry);

    private final EventBusHook eventHook = new EventBusHook(bus, pluginScheduler, tutorialController);
    private final StartupHook startupHook = new StartupHook(tutorialController, templateController, null);

    private final BukkitCommandManager commandManager = new BukkitCommandManager(this);

    @Override
    public void onEnable() {

        generalConfig.reload();
        messageConfig.reload();
        templateController.reload(); //sloppy and bad but i don't care at this point - still 100% better than all of our competitors

        PluginManager manager = getServer().getPluginManager();

        manager.registerEvents(eventHook,this);
        manager.registerEvents(startupHook,this);
        manager.registerEvents(lockHook,this);

        commandManager.registerCommand(new TutorialCommand(tutorialController,templateController, messageConfig.getConfig(), bus));

    }

    @Override
    public void onDisable() {
        tutorialController.close();
    }
}
