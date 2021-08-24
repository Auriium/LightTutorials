package xyz.auriium.opentutorial.core.tutorial.stage.player;

import xyz.auriium.opentutorial.core.platform.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

public class SoundStageConsumer implements StageConsumer<SoundStage> {

    private final TeachableRegistry registry;

    public SoundStageConsumer(TeachableRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void started(SoundStage options, Tutorial continuable) {
        registry.getAudienceByUUID(continuable.getIdentifier()).ifPresent(teachable -> {
            teachable.playSound(options.getSoundString(), options.getVolume(), options.getPitch());
        });

        continuable.fireNext();
    }

    @Override
    public Class<SoundStage> stageClass() {
        return SoundStage.class;
    }
}
