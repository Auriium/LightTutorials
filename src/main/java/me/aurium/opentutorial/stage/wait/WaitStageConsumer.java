package me.aurium.opentutorial.stage.wait;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.StageConsumer;
import org.bukkit.plugin.java.JavaPlugin;

public class WaitStageConsumer implements StageConsumer<WaitStage> {

    private final JavaPlugin plugin;

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
}
