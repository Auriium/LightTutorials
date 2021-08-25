package xyz.auriium.opentutorial.core.tutorial.stage.killbirds;

import xyz.auriium.opentutorial.core.tutorial.StageLocalValue;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.AwaitConsumer;

public class KillABirdConsumer implements AwaitConsumer<KillABirdStage, KillABirdEvent> {

    @Override
    public Class<KillABirdEvent> eventClass() {
        return KillABirdEvent.class;
    }

    @Override
    public void started1(KillABirdStage options, Tutorial continuable) {
        continuable.localStorage().register("kills", new StageLocalValue<>(0, true, close -> {}));
    }

    @Override
    public void consume(KillABirdStage stage, KillABirdEvent event, Tutorial tutorial) {
        tutorial.localStorage().<Integer>retrieveOptional("kills").ifPresent(kills -> {
            int birds = kills.get() + 1;

            kills.setValue(birds);

            if (birds >= stage.getBirdsToKill()) {
                tutorial.fireNext();
            }
        });
    }

    @Override
    public Class<KillABirdStage> stageClass() {
        return KillABirdStage.class;
    }
}
