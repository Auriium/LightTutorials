package xyz.auriium.opentutorial.core.types.player;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.BasicConsumer;
import xyz.auriium.opentutorial.core.consumer.StageException;
import xyz.auriium.opentutorial.core.consumer.StageMissingServiceSupplier;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.consumer.stage.StageLocalValue;
import xyz.auriium.opentutorial.core.platform.UserLocker;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.TutorialStorage;

import java.util.Map;

public class LockableStageHandler implements BasicConsumer<LockableStage> {
    @Override
    public String identifier() {
        return "lock";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {
        boolean movement = Interpret.getRequired(Identifiers.LOCK_MOVE,map,FlexibleType::getBoolean);
        boolean view = Interpret.getAlternative(Identifiers.LOCK_VIEW,map,FlexibleType::getBoolean,false);

        return new LockableStage(movement,view);
    }

    @Override
    public void stageStarted(LockableStage options, Tutorial tutorial) throws StageException {
        UserLocker userLocker = tutorial.getPlatform()
                .serviceRegistry()
                .retrieve(UserLocker.class)
                .orElseThrow(new StageMissingServiceSupplier("UserLocker"));

        TutorialStorage storage = tutorial.localStorage();

        storage.register("lock_cleanup", new StageLocalValue<>(false, false, val -> userLocker.removeOne(tutorial.getIdentifier())));

        userLocker.setLockedMovement(tutorial.getIdentifier(), options.isLockMovement());
        userLocker.setLockedView(tutorial.getIdentifier(), options.isLockView());

        tutorial.fireNext();

    }

    @Override
    public Class<LockableStage> stageClass() {
        return LockableStage.class;
    }
}
