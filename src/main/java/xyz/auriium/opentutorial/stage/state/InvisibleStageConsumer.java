package xyz.auriium.opentutorial.stage.state;

import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.centralized.server.UUIDRegistry;
import xyz.auriium.opentutorial.stage.BasicStageConsumer;

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
