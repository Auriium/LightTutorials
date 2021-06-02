package xyz.auriium.opentutorial;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginCommand {

    private final JavaPlugin plugin;

    public PluginCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void runCommand(String command) {
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),command);
    }

    public void runAs(CommandSender sender, String command) {
        plugin.getServer().dispatchCommand(sender,command);
    }

}
