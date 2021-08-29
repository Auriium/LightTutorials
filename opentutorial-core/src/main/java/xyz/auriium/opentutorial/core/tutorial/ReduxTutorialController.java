package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.opentutorial.core.InternalDependentModule;
import xyz.auriium.opentutorial.core.consumer.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.consumer.StageException;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.template.Template;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ReduxTutorialController implements TutorialController{

    private final Map<UUID, ReduxData> map = new ConcurrentHashMap<>();

    private final Platform platform;
    private final InternalDependentModule module;
    private final ConsumerCentralizer centralizer; //Prebuilt registry

    public ReduxTutorialController(Platform platform, InternalDependentModule module, ConsumerCentralizer registry) {
        this.platform = platform;
        this.module = module;
        this.centralizer = registry;
    }

    @Override
    public void cancelByUUID(UUID uuid) {
        ReduxData data = map.remove(uuid);

        if (data == null) throw new IllegalStateException("Data is null when it should not be!");

        data.getStorage().closeTutorial();
    }

    @Override
    public Optional<Tutorial> getByUUID(UUID uuid) {
        return Optional.ofNullable(map.get(uuid)).map(ReduxData::getTutorial);
    }

    @Override
    public Tutorial createNew(Template template, UUID owner) {

        if (map.containsKey(owner)) throw new IllegalStateException("User is already in a tutorial: " + owner);

        Tutorial tutorial = new ReduxTutorial(owner, platform, module, this);
        ReduxData data = new ReduxData(new ArrayDeque<>(template.getStages()), tutorial);

        map.put(owner, data);

        return tutorial;
    }

    @Override
    public Tutorial createStage(Template template, UUID owner, int stage) {

        if (map.containsKey(owner)) throw new IllegalStateException("User is already in a tutorial: " + owner);
        if (template.stageNotPresent(stage)) throw new IllegalStateException("No stage exists at that index!");

        Deque<Stage> stages = new ArrayDeque<>();
        stages.add(template.getStages().get(stage));

        Tutorial tutorial = new ReduxTutorial(owner, platform, module, this);
        ReduxData data = new ReduxData(new ArrayDeque<>(stages), tutorial);

        map.put(owner, data);

        return tutorial;
    }


    @Override
    public void closeSingle(UUID uuid) {
        cancelByUUID(uuid);
    }

    @Override
    public void close() {
        map.forEach((a,b) -> cancelByUUID(a));
    }

    void nextSingle(UUID uuid) {
        ReduxData data = map.get(uuid);

        if (data == null) throw new StageException("Attempted to increment stage that does not exist in the stage map!");

        data.getStorage().closeStage();

        Queue<Stage> stages = data.getStageQueue();
        Tutorial tutorial = data.getTutorial();

        if (stages.isEmpty()) {
            cancelByUUID(uuid);
            return;
        }

        centralizer.consumeStage(stages.remove(), tutorial);
    }

    TutorialStorage storage(UUID uuid) {
        ReduxData data = map.get(uuid);

        if (data == null) throw new StageException("Attempted to get storage for stage that does not exist!");

        return data.getStorage();
    }

}
