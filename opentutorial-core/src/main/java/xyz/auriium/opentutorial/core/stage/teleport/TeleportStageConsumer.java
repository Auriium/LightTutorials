package xyz.auriium.opentutorial.core.stage.teleport;

import xyz.auriium.opentutorial.core.StageFailureException;
import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.api.construct.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.BasicStageConsumer;

public class TeleportStageConsumer implements BasicStageConsumer<TeleportStage> {

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
