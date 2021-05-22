package me.aurium.opentutorial.stage.wait;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.StageConsumer;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class WaitStageConsumer implements StageConsumer<WaitStage> {

    private final JavaPlugin plugin;

    private final Map<UUID, BukkitTask> map = new HashMap<>();

    public WaitStageConsumer(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void started(WaitStage stage, Tutorial continuable) {
        plugin.getServer().getScheduler().runTaskLater(plugin, continuable::fireNext, stage.getDelay());
    }

    @Override
    public Class<WaitStage> stageClass() {
        return WaitStage.class;
    }

    @Override
    public void closeSingle(UUID uuid) {

        BukkitTask task = map.remove(uuid);

        if (task != null) {
            task.cancel();
        }

    }

    @Override
    public void closeAll() {
        map.forEach((e,s) -> {
            if (s != null) {
                s.cancel();
            }
        });

        map.clear();
    }
}
