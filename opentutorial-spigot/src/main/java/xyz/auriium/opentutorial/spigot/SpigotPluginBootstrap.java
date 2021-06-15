package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.InvalidCommandArgument;
import co.aikar.commands.MessageType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.platform.base.UserRegistry;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.event.CommonInnerEventBus;
import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.event.outer.HookCentralizer;
import xyz.auriium.opentutorial.core.platform.base.Colorer;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.stage.age.AgeStageConsumer;
import xyz.auriium.opentutorial.core.stage.age.AgeStageSerializer;
import xyz.auriium.opentutorial.core.stage.chat.ChatStageConsumer;
import xyz.auriium.opentutorial.core.stage.chat.ChatStageSerializer;
import xyz.auriium.opentutorial.core.stage.delay.DelaySerializer;
import xyz.auriium.opentutorial.core.stage.delay.DelayStageConsumer;
import xyz.auriium.opentutorial.core.tutorial.impl.CommonConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.impl.CommonTutorialController;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.tutorial.impl.CommonTemplateController;
import xyz.auriium.opentutorial.core.tutorial.Template;
import xyz.auriium.opentutorial.core.tutorial.TemplateController;
import xyz.auriium.opentutorial.spigot.hook.EventBusListener;
import xyz.auriium.opentutorial.spigot.hook.LockListener;
import xyz.auriium.opentutorial.spigot.hook.SpigotHookCentralizer;
import xyz.auriium.opentutorial.spigot.hook.StartupListener;
import xyz.auriium.opentutorial.spigot.stage.*;

public class SpigotPluginBootstrap extends JavaPlugin {

    //oh god it burns my eyes (move to dependency injection to avoid constructorhell or the alternate recursive constructorhell

    //everything here is messy and bad because we didn't design around a certain startup work flow or reloadable servers.
    private final InnerEventBus innerEventBus = new CommonInnerEventBus(tutorialController);
    private final Colorer colorer = new SpigotColorer();
    private final Scheduler scheduler = new SpigotScheduler(this);
    private final UserRegistry<Player> userRegistry = new SpigotUserRegistry(this);

    private final ConfigExceptionHandler exceptionHandler = new SpigotExceptionHandler(userRegistry);
    private final ConfigController configController = new CommonConfigController(exceptionHandler, colorer, getDataFolder().toPath());

    private final LockListener lockListener = new LockListener();

    private final ConsumerCentralizer consumerCentralizer = new CommonConsumerCentralizer(consumers, innerEventBus)
            .register(new ChatStageConsumer(userRegistry),new ChatStageSerializer())
            .register(new AgeStageConsumer(scheduler,userRegistry, configController.getMessageConfig()),new AgeStageSerializer())
            .register(new ClickBlockConsumer(scheduler,userRegistry, configController.getMessageConfig()),new ClickBlockSerializer())
            .register(new CommandStageConsumer(userRegistry),new CommandStageSerializer())
            .register(new DelayStageConsumer(scheduler),new DelaySerializer())
            .register(new InvisibleStageConsumer(userRegistry),new InvisibleStageSerializer())
            .register(new LockStageConsumer(lockListener),new LockStageSerializer())
            .register(new PlainKeywordStageConsumer(scheduler,userRegistry, configController.getMessageConfig()),new PlainKeywordSerializer())
            .register(new TeleportStageConsumer(userRegistry,this.getServer()),new TeleportStageSerializer());

    private final TutorialCentralizer tutorialCentralizer = new TutorialCentralizer(consumerCentralizer, getDataFolder().toPath(), exceptionHandler);
    private final TutorialController tutorialController = new CommonTutorialController(consumerCentralizer);
    private final TemplateController templateController = new CommonTemplateController(tutorialCentralizer.getTutorialsConfig());

    private final StartupListener startupListener = new StartupListener(tutorialController,templateController, configController.getGeneralConfig());
    private final EventBusListener eventBusListener = new EventBusListener(innerEventBus, scheduler, tutorialController);
    private final HookCentralizer spigotHookCentralizer = new SpigotHookCentralizer(this,lockListener,startupListener, eventBusListener);

    private final InitialCentralizer centralizer = new InitialCentralizer(configController, consumerCentralizer, tutorialCentralizer, tutorialController, spigotHookCentralizer);

    private final TutorialCommand command = new TutorialCommand(centralizer, tutorialController,templateController, configController.getMessageConfig(), innerEventBus);


    @Override
    public void onEnable() {
        centralizer.startup();

        BukkitCommandManager manager = new BukkitCommandManager(this);

        manager.getCommandContexts().registerContext(Template.class, c -> {
            String arg = c.getFirstArg();

            return templateController.getByIdentifier(arg).orElseThrow(() -> {
                configController.getMessageConfig().get().invalidTemplateMessage().send(SpigotAudience.wrap(c.getSender()),arg);

                return new InvalidCommandArgument(false);
            });
        });

        manager.getCommandCompletions().registerCompletion("templates",s -> templateController.getTemplateNames());

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
