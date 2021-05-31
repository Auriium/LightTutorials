package me.aurium.opentutorial.stage.state;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.hook.LockHook;
import me.aurium.opentutorial.stage.StageConsumer;

import java.util.UUID;

public class LockStageConsumer implements StageConsumer<LockStage> {

    private final LockHook lockHook;

    public LockStageConsumer(LockHook lockHook) {
        this.lockHook = lockHook;
    }

    @Override
    public void started(LockStage options, Tutorial continuable) {
        UUID uuid = continuable.getIdentifier();

        if (options.isLockMovement()) {
            lockHook.getLockMovement().add(uuid);
        } else {
            lockHook.getLockMovement().remove(uuid);
        }

        if (options.isLockView()) {
            lockHook.getLockView().add(uuid);
        } else {
            lockHook.getLockView().remove(uuid);
        }
    }

    @Override
    public Class<LockStage> stageClass() {
        return LockStage.class;
    }

    @Override
    public void closeSingle(UUID uuid) {
        lockHook.getLockView().remove(uuid);
        lockHook.getLockMovement().remove(uuid);
    }

    @Override
    public void close() {
        lockHook.getLockMovement().clear();
        lockHook.getLockView().clear();
    }
}
