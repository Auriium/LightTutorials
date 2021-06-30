package xyz.auriium.opentutorial.core.stage.lock;

import xyz.auriium.opentutorial.api.construct.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.UUID;

public class LockableConsumer implements StageConsumer<LockableStage> {

    private final Lockable lockable;

    public LockableConsumer(Lockable lockable) {
        this.lockable = lockable;
    }

    @Override
    public void started(LockableStage options, Tutorial continuable) {
        UUID id = continuable.getIdentifier();
        lockable.setLockedMovement(id,options.isLockMovement());
        lockable.setLockedView(id,options.isLockView());
    }

    @Override
    public Class<LockableStage> stageClass() {
        return LockableStage.class;
    }

    @Override
    public void closeSingle(UUID uuid) {
        lockable.removeOne(uuid);
    }

    @Override
    public void close() {
        lockable.removeAll();
    }
}
