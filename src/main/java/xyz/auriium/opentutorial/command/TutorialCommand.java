package xyz.auriium.opentutorial.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import xyz.auriium.opentutorial.centralized.TutorialController;
import xyz.auriium.opentutorial.centralized.config.messages.ConfMessages;
import xyz.auriium.opentutorial.centralized.registry.EventBus;
import xyz.auriium.opentutorial.centralized.template.TemplateController;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.stage.response.ClickableEvent;

import java.util.UUID;

/**
 * What an ugly class - let's use branch!
 */
public class TutorialCommand extends BaseCommand {


    private final TutorialController controller;
    private final TemplateController templateController;

    private final ConfMessages messages;

    private final EventBus bus;

    public TutorialCommand(TutorialController controller, TemplateController templateController, ConfMessages messages, EventBus bus) {
        this.controller = controller;
        this.templateController = templateController;
        this.messages = messages;
        this.bus = bus;
    }

    @HelpCommand
    @CommandPermission("opentutorial.help")
    public void help(Player player, CommandHelp help) {
        player.sendMessage(color("&7-------[ &9OpenTutorial&7 ]-------"));

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

        templateController.loadTemplate(template).ifPresentOrElse(
                templ -> controller.createNew(templ, user.getUniqueId()).fireNext(),
                () -> sender.sendMessage(color("&9OpenTutorial &7» &cNo template found with that identifier!")));
    }

    @Subcommand("playpoint")
    public void playPoint(Player sender, @Optional Player target, String template, int point) {
        Player user = target == null ? sender : target;



        templateController.loadTemplate(template).ifPresentOrElse(templ -> {
            if (!templ.hasStage(point)) {
                sender.sendMessage(color("&9OpenTutorial &7» &cNo stage found at that point number!"));
                return;
            }

            controller.createStage(templ, user.getUniqueId(), point);

        }, () -> sender.sendMessage(color("&9OpenTutorial &7» &cNo template found with that identifier!")));
    }

    @Subcommand("option")
    public void option(Player sender, int option) {
        UUID uuid = sender.getUniqueId();

        controller.getByUUID(uuid).ifPresentOrElse(
                tutorial -> bus.fire(new ClickableEvent(option),tutorial),
                () -> sender.sendMessage(color("&9OpenTutorial &7» &cYou are not in a tutorial!"))
        );


    }

    @Subcommand("quit")
    @CommandPermission("opentutorial.quit")
    public void leave(Player sender) {
        UUID uuid = sender.getUniqueId();

        if (controller.getByUUID(uuid).isEmpty()) {
            sender.sendMessage(color("&9OpenTutorial &7» &cYou are not in a tutorial!"));
            return;
        }

        controller.cancelByUUID(uuid);
    }



    String color(String string) {
        return ChatColor.translateAlternateColorCodes('&',string);
    }

}
