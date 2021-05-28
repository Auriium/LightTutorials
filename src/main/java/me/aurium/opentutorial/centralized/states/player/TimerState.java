package me.aurium.opentutorial.centralized.states.player;

import me.aurium.opentutorial.centralized.states.State;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class TimerState implements State {

    private BukkitTask task;

    public void activate() {
        task.cancel();
        task = Bukkit.getScheduler().runTask(null,  () -> {

        });
    }

    @Override
    public void deactivate() {
        task.cancel();
    }
}
