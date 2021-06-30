package xyz.auriium.opentutorial.core.stage.playsound;

import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.api.construct.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.BasicStageConsumer;

public class SoundStageConsumer implements BasicStageConsumer<SoundStage> {

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
