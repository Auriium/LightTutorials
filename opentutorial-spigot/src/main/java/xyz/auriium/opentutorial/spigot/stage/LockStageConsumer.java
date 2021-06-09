package xyz.auriium.opentutorial.spigot.stage;

import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.spigot.hook.LockListener;

import java.util.UUID;

public class LockStageConsumer implements StageConsumer<LockStage> {

    private final LockListener lockListener;

    public LockStageConsumer(LockListener lockListener) {
        this.lockListener = lockListener;
    }

    @Override
    public void started(LockStage options, Tutorial continuable) {
        UUID uuid = continuable.getIdentifier();

        if (options.isLockMovement()) {
            lockListener.getLockMovement().add(uuid);
        } else {
            lockListener.getLockMovement().remove(uuid);
        }

        if (options.isLockView()) {
            lockListener.getLockView().add(uuid);
        } else {
            lockListener.getLockView().remove(uuid);
        }
    }

    @Override
    public Class<LockStage> stageClass() {
        return LockStage.class;
    }

    @Override
    public void closeSingle(UUID uuid) {
        lockListener.getLockView().remove(uuid);
        lockListener.getLockMovement().remove(uuid);
    }

    @Override
    public void close() {
        lockListener.getLockMovement().clear();
        lockListener.getLockView().clear();
    }
}
