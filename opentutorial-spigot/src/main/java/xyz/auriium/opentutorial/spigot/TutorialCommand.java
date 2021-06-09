package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.config.types.messages.MessageConfig;
import xyz.auriium.opentutorial.core.event.inner.InnerEventBus;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.tutorial.template.TemplateController;
import xyz.auriium.opentutorial.spigot.stage.ClickableEvent;

import java.util.UUID;

/**
 * What an ugly class - let's use branch!
 */
public class TutorialCommand extends BaseCommand {


    private final TutorialController tutorialController;
    private final TemplateController templateController;

    private final MessageConfig messages;

    private final InnerEventBus bus;

    public TutorialCommand(TutorialController tutorialController, TemplateController templateController, MessageConfig messages, InnerEventBus bus) {
        this.tutorialController = tutorialController;
        this.templateController = templateController;
        this.messages = messages;
        this.bus = bus;
    }

    @HelpCommand
    @CommandPermission("opentutorial.help")
    public void help(Player player, CommandHelp help) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7-------[ &9OpenTutorial&7 ]-------"));

        help.showHelp();
    }

    @Subcommand("reload")
    @CommandPermission("opentutorial.reload")
    public void reload(Player player) {

    }

    @Subcommand("play")
    @CommandPermission("opentutorial.play")
    public void play(Player sender, @Optional Player target, String template) {
        Player user = target == null ? sender : target;

        if (tutorialController.getByUUID(user.getUniqueId()).isPresent()) {
            messages.alreadyInTutorialMessage().send(SpigotAudience.wrap(sender));
            return;
        }

        templateController.getByIdentifier(template).ifPresentOrElse(
                templ -> tutorialController.createNew(templ, user.getUniqueId()).fireNext(),
                () -> messages.invalidTemplateMessage().send(SpigotAudience.wrap(sender), template)
        );
    }

    @Subcommand("playpoint")
    @CommandPermission("opentutorial.play")
    public void playPoint(Player sender, @Optional Player target, String template, int point) {
        Player user = target == null ? sender : target;

        if (tutorialController.getByUUID(user.getUniqueId()).isPresent()) {
            messages.alreadyInTutorialMessage().send(SpigotAudience.wrap(sender));
            return;
        }

        templateController.getByIdentifier(template).ifPresentOrElse(templ -> {
            if (!templ.hasStage(point)) {
                messages.invalidStageMessage().send(SpigotAudience.wrap(sender),point,template);
                return;
            }

            tutorialController.createStage(templ, user.getUniqueId(), point);

        }, () -> messages.invalidTemplateMessage().send(SpigotAudience.wrap(sender),template));
    }

    @Subcommand("option")
    public void option(Player sender, int option) {
        UUID uuid = sender.getUniqueId();

        tutorialController.getByUUID(uuid).ifPresentOrElse(
                tutorial -> bus.fire(new ClickableEvent(option),tutorial),
                () -> messages.notInTutorialMessage().send(SpigotAudience.wrap(sender))
        );


    }

    @Subcommand("quit")
    @CommandPermission("opentutorial.quit")
    public void leave(Player sender) {
        UUID uuid = sender.getUniqueId();

        if (tutorialController.getByUUID(uuid).isEmpty()) {
            messages.notInTutorialMessage().send(SpigotAudience.wrap(sender));
            return;
        }

        tutorialController.cancelByUUID(uuid);
    }

}
