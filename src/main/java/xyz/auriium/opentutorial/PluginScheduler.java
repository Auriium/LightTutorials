package xyz.auriium.opentutorial;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Wrapping class for the scheduler
 */
public class PluginScheduler {

    private final JavaPlugin plugin;

    public PluginScheduler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public BukkitTask runLater(Runnable runnable, long delay) {
        return plugin.getServer().getScheduler().runTaskLater(plugin,runnable,delay);
    }

    public BukkitTask runRepeated(Runnable runnable, long delay) {
        return plugin.getServer().getScheduler().runTaskTimer(plugin,runnable,0L,delay);
    }

}
