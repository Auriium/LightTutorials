package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BukkitCommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.control.CommandCentralizer;

public class SpigotCommandCentralizer implements CommandCentralizer {

    private final JavaPlugin plugin;
    private final TutorialCommand tutorialCommand;

    public SpigotCommandCentralizer(JavaPlugin plugin, TutorialCommand tutorialCommand) {
        this.plugin = plugin;
        this.tutorialCommand = tutorialCommand;
    }


    @Override
    public void startup() {
        new BukkitCommandManager(plugin).registerCommand(tutorialCommand);
    }

    @Override
    public void reload() {

    }

    @Override
    public void shutdown() {

    }
}
