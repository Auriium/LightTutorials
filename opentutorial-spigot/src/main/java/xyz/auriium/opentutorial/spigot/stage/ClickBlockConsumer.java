package xyz.auriium.opentutorial.spigot.stage;

import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.AudienceRegistry;
import xyz.auriium.opentutorial.core.UserRegistry;
import xyz.auriium.opentutorial.core.config.types.messages.MessageConfig;
import xyz.auriium.opentutorial.core.model.Scheduler;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.AbstractDelayConsumer;
import xyz.auriium.opentutorial.spigot.SpigotAudience;

import java.util.UUID;

public class ClickBlockConsumer extends AbstractDelayConsumer<ClickBlockStage,ClickBlockEvent> {

    public ClickBlockConsumer(Scheduler scheduler, AudienceRegistry registry, MessageConfig config) {
        super(scheduler, registry, config);
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
