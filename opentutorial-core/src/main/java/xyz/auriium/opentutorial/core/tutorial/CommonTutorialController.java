package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.opentutorial.core.InternalDependentModule;
import xyz.auriium.opentutorial.core.consumer.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.template.Template;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CommonTutorialController implements TutorialController{

    private final Map<UUID, Tutorial> map = new ConcurrentHashMap<>();

    private final Platform platform;
    private final InternalDependentModule module;
    private final ConsumerCentralizer centralizer; //Prebuilt registry

    public CommonTutorialController(Platform platform, InternalDependentModule module, ConsumerCentralizer registry) {
        this.platform = platform;
        this.module = module;
        this.centralizer = registry;
    }

    @Override
    public Optional<Tutorial> cancelByUUID(UUID uuid) {
        Tutorial tutorial = map.remove(uuid);

        if (tutorial != null) {
            tutorial.fireCancel();

            return Optional.of(tutorial);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Tutorial> getByUUID(UUID uuid) {
        return Optional.ofNullable(map.get(uuid));
    }

    @Override
    public Tutorial createNew(Template template, UUID owner) {

        if (map.containsKey(owner)) throw new IllegalStateException("User is already in a tutorial: " + owner);

        Tutorial tutorial = new CommonTutorial(owner, new ArrayDeque<>(template.getStages()), centralizer, platform, module);
        map.put(owner, tutorial);

        return tutorial;
    }

    @Override
    public Tutorial createStage(Template template, UUID owner, int stage) {

        if (map.containsKey(owner)) throw new IllegalStateException("User is already in a tutorial: " + owner);
        if (template.stageNotPresent(stage)) throw new IllegalStateException("No stage exists at that index!");

        Deque<Stage> stages = new ArrayDeque<>();
        stages.add(template.getStages().get(stage));

        Tutorial tutorial = new CommonTutorial(owner, new ArrayDeque<>(stages), centralizer, platform, module);
        map.put(owner, tutorial);

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
}
