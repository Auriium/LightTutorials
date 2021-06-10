package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.InitialCentralizer;
import xyz.auriium.opentutorial.core.config.ConfigHolder;
import xyz.auriium.opentutorial.core.config.types.messages.MessageConfig;
import xyz.auriium.opentutorial.core.event.inner.InnerEventBus;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.tutorial.template.TemplateController;
import xyz.auriium.opentutorial.spigot.stage.ClickableEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * What an ugly class - let's use branch!
 */
@CommandAlias("tutorial|opentutorial")
public class TutorialCommand extends BaseCommand {



    private final InitialCentralizer initialCentralizer;
    private final TutorialController tutorialController;
    private final TemplateController templateController;

    private final ConfigHolder<MessageConfig> messages;

    private final InnerEventBus bus;

    public TutorialCommand(InitialCentralizer initialCentralizer, TutorialController tutorialController, TemplateController templateController, ConfigHolder<MessageConfig> messages, InnerEventBus bus) {
        this.initialCentralizer = initialCentralizer;
        this.tutorialController = tutorialController;
        this.templateController = templateController;
        this.messages = messages;
        this.bus = bus;
    }

    @HelpCommand
    @CommandPermission("opentutorial.help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("reload")
    @CommandPermission("opentutorial.reload")
    public void reload(Player player) {
        initialCentralizer.reload();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        messages.get().reloadMessage().send(SpigotAudience.wrap(player), formatter.format(date));
    }

    @Subcommand("play")
    @CommandPermission("opentutorial.play")
    public void play(Player sender, String template, @Optional Player target) {
        Player user = target == null ? sender : target;

        if (tutorialController.getByUUID(user.getUniqueId()).isPresent()) {
            messages.get().alreadyInTutorialMessage().send(SpigotAudience.wrap(sender));
            return;
        }

        templateController.getByIdentifier(template).ifPresentOrElse(
                templ -> tutorialController.createNew(templ, user.getUniqueId()).fireNext(),
                () -> messages.get().invalidTemplateMessage().send(SpigotAudience.wrap(sender), template)
        );
    }

    @Subcommand("playpoint")
    @CommandPermission("opentutorial.play")
    public void playPoint(Player sender, String template, int point, @Optional Player target) {
        Player user = target == null ? sender : target;

        int subpoint = point - 1;

        if (tutorialController.getByUUID(user.getUniqueId()).isPresent()) {
            messages.get().alreadyInTutorialMessage().send(SpigotAudience.wrap(sender));
            return;
        }

        templateController.getByIdentifier(template).ifPresentOrElse(templ -> {
            if (templ.stageNotPresent(subpoint)) {
                messages.get().invalidStageMessage().send(SpigotAudience.wrap(sender),point,template);
                return;
            }

            tutorialController.createStage(templ, user.getUniqueId(), subpoint);

        }, () -> messages.get().invalidTemplateMessage().send(SpigotAudience.wrap(sender),template));
    }

    @Subcommand("option")
    public void option(Player sender, int option) {
        UUID uuid = sender.getUniqueId();

        tutorialController.getByUUID(uuid).ifPresentOrElse(
                tutorial -> bus.fire(new ClickableEvent(option),tutorial),
                () -> messages.get().notInTutorialMessage().send(SpigotAudience.wrap(sender))
        );


    }

    @Subcommand("quit")
    @CommandPermission("opentutorial.quit")
    public void leave(Player sender) {
        UUID uuid = sender.getUniqueId();

        if (tutorialController.getByUUID(uuid).isEmpty()) {
            messages.get().notInTutorialMessage().send(SpigotAudience.wrap(sender));
            return;
        }

        tutorialController.cancelByUUID(uuid);
    }

}
