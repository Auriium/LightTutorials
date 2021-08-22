package xyz.auriium.opentutorial.core.tutorial.impl;

import xyz.auriium.opentutorial.core.platform.base.PlatformlessLocation;
import xyz.auriium.opentutorial.core.platform.base.Teachable;
import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.api.construct.Template;
import xyz.auriium.opentutorial.api.construct.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.api.construct.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.*;

/**
 * Controller as well as entry point for closeables
 */
public class CommonTutorialController implements TutorialController {

    static class TutorialData {
        private final Tutorial tutorial;
        private final PlatformlessLocation location;

        TutorialData(Tutorial tutorial, PlatformlessLocation location) {
            this.tutorial = tutorial;
            this.location = location;
        }

        public Tutorial getTutorial() {
            return tutorial;
        }

        public PlatformlessLocation getLocation() {
            return location;
        }
    }

    private final Map<UUID, TutorialData> map;

    private final ConsumerCentralizer registry; //Prebuilt registry
    private final TeachableRegistry teachableRegistry;

    public CommonTutorialController(ConsumerCentralizer registry, TeachableRegistry teachableRegistry) {
        this.teachableRegistry = teachableRegistry;
        this.map = new HashMap<>();

        this.registry = registry;
    }


    public <T extends Stage> void consumeStage(T stage, Tutorial tutorial) {

        StageConsumer<T> c = registry.getConsumer(stage).orElseThrow(() -> new NoConsumerException("No logic found for stage of type " + stage.getClass().getName()));

        c.started(stage,tutorial);
    }

    @Override
    public Optional<Tutorial> cancelByUUID(UUID uuid) {
        teachableRegistry.getAudienceByUUID(uuid).ifPresent(teachable -> {
            TutorialData data = map.get(uuid);

            if (data != null) {
                teachable.teleport(data.getLocation());
            }
        });

        registry.closeSingle(uuid);

        TutorialData data = map.remove(uuid);

        if (data == null) return Optional.empty();

        return Optional.of(data.getTutorial());
    }

    @Override
    public Optional<Tutorial> getByUUID(UUID uuid) {
        TutorialData data = map.get(uuid);

        if (data == null) return Optional.empty();

        return Optional.of(data.getTutorial());
    }

    @Override
    public Tutorial createNew(Template template, UUID owner) {
       if (map.containsKey(owner)) throw new IllegalStateException("User is already in a tutorial: " + owner);
       Teachable teachable = teachableRegistry.getAudienceByUUID(owner).orElseThrow(() -> new IllegalStateException("User is activating tutorial but not on server!"));

       Tutorial tutorial = new CommonTutorial(owner,new ArrayDeque<>(template.getStages()),this);

       map.put(owner, new TutorialData(tutorial,teachable.getLocation()));

       return tutorial;
    }

    @Override
    public Tutorial createStage(Template template, UUID owner, int stage) {
        if (map.containsKey(owner)) throw new IllegalStateException("User is already in a tutorial: " + owner);
        if (template.stageNotPresent(stage)) throw new IllegalStateException("No stage exists at that index!");
        Teachable teachable = teachableRegistry.getAudienceByUUID(owner).orElseThrow(() -> new IllegalStateException("User is activating tutorial but not on server!"));


        Deque<Stage> stages = new ArrayDeque<>();
        stages.add(template.getStages().get(stage));

        Tutorial tutorial = new CommonTutorial(owner,new ArrayDeque<>(stages),this);

        map.put(owner, new TutorialData(tutorial,teachable.getLocation()));

        return tutorial;
    }

    @Override
    public void closeSingle(UUID uuid) {
        cancelByUUID(uuid);
    }

    @Override
    public void close() {
        registry.close();
        map.clear();
    }

    @Override
    public void startup() {
        //no-ops
    }

    @Override
    public void reload() {
        close();
    }

    @Override
    public void shutdown() {
        close();
    }
}
