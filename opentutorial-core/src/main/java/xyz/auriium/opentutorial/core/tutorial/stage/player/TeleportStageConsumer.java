package xyz.auriium.opentutorial.core.tutorial.stage.player;

import xyz.auriium.opentutorial.core.platform.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

public class TeleportStageConsumer implements xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer<TeleportStage> {

    private final TeachableRegistry userRegistry;

    public TeleportStageConsumer(TeachableRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    @Override
    public void started(TeleportStage options, Tutorial continuable) {

        userRegistry.getAudienceByUUID(continuable.getIdentifier()).ifPresent(player -> {
            player.teleport(options.getLocation());
        });

        continuable.fireNext();
    }

    @Override
    public Class<TeleportStage> stageClass() {
        return TeleportStage.class;
    }
}
