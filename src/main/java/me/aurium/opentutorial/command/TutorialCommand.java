package me.aurium.opentutorial.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * What an ugly class - let's use branch!
 */
public class TutorialCommand extends BaseCommand {

    private final JavaPlugin plugin;

    public TutorialCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @HelpCommand
    public static void onList(Player player, CommandHelp help) {
        player.sendMessage(color("&7-------[ &9OpenTutorial&7 ]-------"));

        help.showHelp();
    }

    @Subcommand("reload")
    public static void reload(Player player) {

    }

    @Subcommand("play")
    public static void play(Player sender, @Optional Player target, String template) {

    }

    public static void playPoint(Player sender, @Optional Player target, String template, int point) {

    }

    static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&',string);
    }

}
