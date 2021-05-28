package me.aurium.opentutorial.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import me.aurium.opentutorial.centralized.TutorialController;
import me.aurium.opentutorial.centralized.template.TemplateController;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * What an ugly class - let's use branch!
 */
public class TutorialCommand extends BaseCommand {

    private final TutorialController controller;
    private final TemplateController templateController;

    public TutorialCommand(TutorialController controller, TemplateController templateController) {
        this.controller = controller;
        this.templateController = templateController;
    }

    @HelpCommand
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

        templateController.loadTemplate(template).ifPresentOrElse(templ -> {
            controller.createNew(templ, user.getUniqueId()).fireNext();
        }, () -> sender.sendMessage(color("&9OpenTutorial &7» &cNo template found with that identifier!")));
    }

    @Subcommand("quit")
    @CommandPermission("opentutorial.quit")
    public void leave(Player sender) {
        UUID uuid = sender.getUniqueId();

        if (!controller.isInTutorial(uuid)) {

        }
    }

    public void playPoint(Player sender, @Optional Player target, String template, int point) {

    }

    String color(String string) {
        return ChatColor.translateAlternateColorCodes('&',string);
    }

}
