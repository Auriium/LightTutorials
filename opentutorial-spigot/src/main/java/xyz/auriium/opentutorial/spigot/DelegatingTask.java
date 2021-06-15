package xyz.auriium.opentutorial.spigot;

import org.bukkit.scheduler.BukkitTask;
import xyz.auriium.opentutorial.core.platform.base.SchedulerTask;

public class DelegatingTask implements SchedulerTask {

    private final BukkitTask task;

    public DelegatingTask(BukkitTask task) {
        this.task = task;
    }

    @Override
    public void cancel() {
        this.task.cancel();
    }
}
