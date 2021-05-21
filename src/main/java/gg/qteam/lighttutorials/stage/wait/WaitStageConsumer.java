package gg.qteam.lighttutorials.stage.wait;

import gg.qteam.lighttutorials.model.SequentialTutorial;
import gg.qteam.lighttutorials.stage.StageConsumer;
import org.bukkit.plugin.java.JavaPlugin;

public class WaitStageConsumer implements StageConsumer<WaitStage> {

    private final JavaPlugin plugin;

    public WaitStageConsumer(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void started(WaitStage stage, SequentialTutorial continuable) {
        plugin.getServer().getScheduler().runTaskLater(plugin, continuable::fireContinue, stage.getDelay());
    }

    @Override
    public Class<WaitStage> stageClass() {
        return WaitStage.class;
    }
}
