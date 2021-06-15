package xyz.auriium.opentutorial.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.platform.base.Cycleable;

import java.nio.file.Path;

public class SpigotLauncher {

    private final JavaPlugin plugin;
    private final Path folder;

    public SpigotLauncher(JavaPlugin plugin, Path folder) {
        this.plugin = plugin;
        this.folder = folder;
    }

    public Cycleable getInit() {
        return null;
    }
}
