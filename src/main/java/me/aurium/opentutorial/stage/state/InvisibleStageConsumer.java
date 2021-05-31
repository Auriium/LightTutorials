package me.aurium.opentutorial.stage.state;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.centralized.server.UUIDRegistry;
import me.aurium.opentutorial.stage.BasicStageConsumer;

public class InvisibleStageConsumer implements BasicStageConsumer<InvisibleStage> {

    private final UUIDRegistry registry;

    public InvisibleStageConsumer(UUIDRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void started(InvisibleStage options, Tutorial continuable) {
        registry.getPlayer(continuable.getIdentifier()).ifPresent(player -> player.setInvisible(options.isOn()));

        continuable.fireNext();
    }

    @Override
    public Class<InvisibleStage> stageClass() {
        return InvisibleStage.class;
    }
}
