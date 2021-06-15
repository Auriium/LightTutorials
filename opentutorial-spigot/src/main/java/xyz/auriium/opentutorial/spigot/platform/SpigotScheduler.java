package xyz.auriium.opentutorial.spigot.platform;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.SchedulerTask;

public class SpigotScheduler implements Scheduler {

    private final JavaPlugin plugin;

    public SpigotScheduler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public SchedulerTask run(Runnable runnable) {
        return new DelegatingTask(plugin.getServer().getScheduler().runTask(plugin,runnable));
    }

    @Override
    public SchedulerTask runLater(Runnable runnable, long delay) {
        return new DelegatingTask(plugin.getServer().getScheduler().runTaskLater(plugin,runnable,delay));
    }

    @Override
    public SchedulerTask runRepeated(Runnable runnable, long period) {
        return new DelegatingTask(plugin.getServer().getScheduler().runTaskTimer(plugin,runnable,0L,period));
    }
}
