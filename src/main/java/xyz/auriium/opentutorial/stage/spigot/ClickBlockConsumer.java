package xyz.auriium.opentutorial.stage.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.PluginScheduler;
import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.stage.await.AbstractAwaitConsumer;
import xyz.auriium.opentutorial.stage.await.AbstractDelayConsumer;

public class ClickBlockConsumer extends AbstractDelayConsumer<ClickBlockStage,ClickBlockEvent> {


    public ClickBlockConsumer(PluginScheduler scheduler) {
        super(scheduler);
    }

    @Override
    public Class<ClickBlockStage> stageClass() {
        return ClickBlockStage.class;
    }

    @Override
    public void consume(ClickBlockStage stage, ClickBlockEvent event, Tutorial tutorial) {
        if (event.getX() == stage.getX() && event.getY() == stage.getY() && event.getZ() == stage.getZ()) {
            tutorial.fireNext();
        }
    }

    @Override
    public Class<ClickBlockEvent> eventClass() {
        return ClickBlockEvent.class;
    }
}
