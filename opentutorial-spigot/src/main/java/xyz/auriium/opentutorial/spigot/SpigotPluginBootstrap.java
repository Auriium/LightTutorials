package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.InvalidCommandArgument;
import co.aikar.commands.MessageKeys;
import co.aikar.commands.MessageType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.InitialCentralizer;
import xyz.auriium.opentutorial.core.UserRegistry;
import xyz.auriium.opentutorial.core.config.CommonConfigCentralizer;
import xyz.auriium.opentutorial.core.config.ConfigCentralizer;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.config.TutorialCentralizer;
import xyz.auriium.opentutorial.core.event.inner.CommonEventBus;
import xyz.auriium.opentutorial.core.event.inner.InnerEventBus;
import xyz.auriium.opentutorial.core.event.outer.HookCentralizer;
import xyz.auriium.opentutorial.core.model.Colorer;
import xyz.auriium.opentutorial.core.model.Scheduler;
import xyz.auriium.opentutorial.core.tutorial.CommonConsumerRegistry;
import xyz.auriium.opentutorial.core.tutorial.CommonTutorialController;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.tutorial.template.CommonTemplateController;
import xyz.auriium.opentutorial.core.tutorial.template.Template;
import xyz.auriium.opentutorial.core.tutorial.template.TemplateController;
import xyz.auriium.opentutorial.spigot.hook.EventBusListener;
import xyz.auriium.opentutorial.spigot.hook.LockListener;
import xyz.auriium.opentutorial.spigot.hook.SpigotHookCentralizer;
import xyz.auriium.opentutorial.spigot.hook.StartupListener;
import xyz.auriium.opentutorial.spigot.stage.*;

public class SpigotPluginBootstrap extends JavaPlugin {

    //oh god it burns my eyes (move to dependency injection to avoid constructorhell or the alternate recursive constructorhell

    //everything here is messy and bad because we didn't design around a certain startup work flow or reloadable servers.
    private final InnerEventBus eventBus = new CommonEventBus();
    private final Colorer colorer = new SpigotColorer();
    private final Scheduler scheduler = new SpigotScheduler(this);
    private final UserRegistry<Player> userRegistry = new SpigotUserRegistry(this);

    private final ConfigExceptionHandler exceptionHandler = new SpigotExceptionHandler(userRegistry);
    private final ConfigCentralizer configCentralizer = new CommonConfigCentralizer(exceptionHandler, colorer, getDataFolder().toPath());

    private final LockListener lockListener = new LockListener();

    private final ConsumerRegistry consumerRegistry = new CommonConsumerRegistry(eventBus)
            .register(new ChatStageConsumer(userRegistry),new ChatStageSerializer())
            .register(new AgeStageConsumer(scheduler,userRegistry,configCentralizer.getMessageConfig()),new AgeStageSerializer())
            .register(new ClickBlockConsumer(scheduler,userRegistry,configCentralizer.getMessageConfig()),new ClickBlockSerializer())
            .register(new CommandStageConsumer(userRegistry),new CommandStageSerializer())
            .register(new DelayStageConsumer(scheduler),new DelaySerializer())
            .register(new InvisibleStageConsumer(userRegistry),new InvisibleStageSerializer())
            .register(new LockStageConsumer(lockListener),new LockStageSerializer())
            .register(new PlainKeywordStageConsumer(scheduler,userRegistry,configCentralizer.getMessageConfig()),new PlainKeywordSerializer());

    private final TutorialCentralizer tutorialCentralizer = new TutorialCentralizer(consumerRegistry, getDataFolder().toPath(), exceptionHandler);
    private final TutorialController tutorialController = new CommonTutorialController(consumerRegistry);
    private final TemplateController templateController = new CommonTemplateController(tutorialCentralizer.getTutorialsConfig());

    private final StartupListener startupListener = new StartupListener(tutorialController,templateController, configCentralizer.getGeneralConfig());
    private final EventBusListener eventBusListener = new EventBusListener(eventBus, scheduler, tutorialController);
    private final HookCentralizer spigotHookCentralizer = new SpigotHookCentralizer(this,lockListener,startupListener, eventBusListener);

    private final InitialCentralizer centralizer = new InitialCentralizer(configCentralizer, consumerRegistry, tutorialCentralizer, tutorialController, spigotHookCentralizer);

    private final TutorialCommand command = new TutorialCommand(centralizer, tutorialController,templateController,configCentralizer.getMessageConfig(),eventBus);


    @Override
    public void onEnable() {
        centralizer.startup();

        BukkitCommandManager manager = new BukkitCommandManager(this);

        manager.setFormat(MessageType.HELP, ChatColor.GRAY, ChatColor.BLUE, ChatColor.GRAY);
        manager.setFormat(MessageType.ERROR, ChatColor.RED);
        manager.setFormat(MessageType.SYNTAX, ChatColor.GRAY, ChatColor.BLUE);
        manager.setFormat(MessageType.INFO, ChatColor.GRAY, ChatColor.BLUE);
        manager.enableUnstableAPI("help");
        manager.registerCommand(command);
    }

    @Override
    public void onDisable() {
        centralizer.shutdown();
    }
}
