package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.event.chat.ClickableEvent;
import xyz.auriium.opentutorial.core.platform.base.UserRegistry;
import xyz.auriium.opentutorial.core.platform.impl.PlatformDependentLoader;
import xyz.auriium.opentutorial.api.construct.Template;
import xyz.auriium.opentutorial.core.tutorial.TemplateController;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.spigot.gui.ListMenu;
import xyz.auriium.opentutorial.spigot.gui.PreCreationMenu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * What an ugly class - let's use branch!
 */
@CommandAlias("tutorial|opentutorial")
public class TutorialCommand extends BaseCommand {

    private final static Logger logger = LoggerFactory.getLogger("OpenTutorial");

    private final UserRegistry<Player> userRegistry;
    private final PlatformDependentLoader<Player> reloader;

    private final ListMenu listMenu;
    private final PreCreationMenu preCreationMenu;

    public TutorialCommand(UserRegistry<Player> userRegistry, PlatformDependentLoader<Player> reloader, ListMenu listMenu, PreCreationMenu preCreationMenu) {
        this.userRegistry = userRegistry;
        this.reloader = reloader;
        this.listMenu = listMenu;
        this.preCreationMenu = preCreationMenu;
    }

    @Subcommand("list")
    @CommandPermission("opentutorial.list")
    public void list(Player player) {
        listMenu.produce().show(player);
    }

    @HelpCommand
    @CommandPermission("opentutorial.help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("reload")
    @CommandPermission("opentutorial.reload")
    public void reload(Player player) {

        try {
            reloader.load();

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();

            reloader.getModule().configController().getMessageConfig().reloadMessage().send(userRegistry.wrapUser(player), formatter.format(date));
        } catch (Exception e) {
            //I SADLY HAVE TO DO THIS FUCKING SHITCODE
            //BECAUSE ACF SUCKS ASS AND FUCKS WITH EXCEPTION HANDLING ANYWAYS
            //SO I HAVE TO DO IT MYSELF IN THIS CRINGE ASS try-catch ABUSE
            //FUCK AIKAR AND HIS SHITCODE
            //CANT WAIT TO SWITCH TO BRANCH

            //FUCK!!!!!!!!

            //the tf2 coconut of opentutorial

            logger.error("Error occurred while reloading!",e);
        }

    }

    @Subcommand("play")
    @CommandPermission("opentutorial.play")
    @CommandCompletion("@templates")
    public void play(Player sender, Template template, @Optional Player target) {
        Player user = target == null ? sender : target;

        TutorialController tutorialController = reloader.getModule().tutorialController();

        if (tutorialController.getByUUID(user.getUniqueId()).isPresent()) {
            reloader.getModule().configController().getMessageConfig().alreadyInTutorialMessage().send(userRegistry.wrapUser(sender));
            return;
        }

        tutorialController.createNew(template,user.getUniqueId()).fireNext();
    }

    @Subcommand("playpoint")
    @CommandPermission("opentutorial.play")
    @CommandCompletion("@templates")
    public void playPoint(Player sender, Template template, int point, @Optional Player target) {
        Player user = target == null ? sender : target;
        TutorialController tutorialController = reloader.getModule().tutorialController();

        int subpoint = point - 1;

        if (tutorialController.getByUUID(user.getUniqueId()).isPresent()) {
            reloader.getModule().configController().getMessageConfig().alreadyInTutorialMessage().send(userRegistry.wrapUser(sender));
            return;
        }

        if (template.stageNotPresent(subpoint)) reloader.getModule().configController().getMessageConfig().invalidStageMessage().send(userRegistry.wrapUser(sender),point,template);
        tutorialController.createStage(template, user.getUniqueId(), subpoint);

    }

    @Subcommand("option")
    public void option(Player sender, int option) {
        UUID uuid = sender.getUniqueId();

        TutorialController tutorialController = reloader.getModule().tutorialController();
        MessageConfig messageConfig = reloader.getModule().configController().getMessageConfig();
        InnerEventBus bus = reloader.getModule().eventBus();

        tutorialController.getByUUID(uuid).ifPresentOrElse(
                tutorial -> bus.fire(new ClickableEvent(option, sender.getUniqueId()),tutorial),
                () -> messageConfig.notInTutorialMessage().send(userRegistry.wrapUser(sender))
        );


    }

    @Subcommand("quit")
    @CommandPermission("opentutorial.quit")
    public void leave(Player sender) {
        UUID uuid = sender.getUniqueId();

        TutorialController tutorialController = reloader.getModule().tutorialController();
        MessageConfig messageConfig = reloader.getModule().configController().getMessageConfig();

        if (tutorialController.getByUUID(uuid).isEmpty()) {
            messageConfig.notInTutorialMessage().send(userRegistry.wrapUser(sender));
            return;
        }

        tutorialController.cancelByUUID(uuid);
    }

    @Subcommand("graphical create")
    @CommandPermission("opentutorial.graphical.create")
    public void graphicalCreate(Player sender, String name, String permission) {

        TemplateController templateController = reloader.getModule().templateController();

        if (templateController.getTemplateNames().stream().map(String::toLowerCase).collect(Collectors.toSet()).contains(name.toLowerCase())) {
            reloader.getModule().configController().getMessageConfig().templateExistsMessage().send(userRegistry.wrapUser(sender));
            return;
        }

        preCreationMenu.produce().show(sender);
    }

}
