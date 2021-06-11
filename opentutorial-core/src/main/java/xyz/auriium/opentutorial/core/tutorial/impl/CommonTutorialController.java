package xyz.auriium.opentutorial.core.tutorial.impl;

import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;
import xyz.auriium.opentutorial.core.tutorial.NoConsumerException;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;
import xyz.auriium.opentutorial.core.tutorial.template.Template;

import java.util.*;

/**
 * Controller as well as entry point for closeables
 */
public class CommonTutorialController implements TutorialController {


    private final Map<UUID, Tutorial> map;
    private final ConsumerRegistry registry; //Prebuilt registry

    public CommonTutorialController(ConsumerRegistry registry) {
        this.map = new HashMap<>();

        this.registry = registry;
    }


    public <T extends Stage> void consumeStage(T stage, Tutorial tutorial) {

        StageConsumer<T> c = registry.getConsumer(stage).orElseThrow(() -> new NoConsumerException("No logic found for stage of type " + stage.getClass().getName()));

        c.started(stage,tutorial);
    }

    @Override
    public Optional<Tutorial> cancelByUUID(UUID uuid) {
        registry.closeSingle(uuid);

        return Optional.ofNullable(map.remove(uuid));
    }

    @Override
    public Optional<Tutorial> getByUUID(UUID uuid) {
        return Optional.ofNullable(map.get(uuid));
    }

    @Override
    public Tutorial createNew(Template template, UUID owner) {
       if (map.containsKey(owner)) throw new IllegalStateException("User is already in a tutorial: " + owner);

       Tutorial tutorial = new CommonTutorial(owner,new ArrayDeque<>(template.getStages()),this);

       map.put(owner, tutorial);

       return tutorial;
    }

    @Override
    public Tutorial createStage(Template template, UUID owner, int stage) {
        if (map.containsKey(owner)) throw new IllegalStateException("User is already in a tutorial: " + owner);
        if (template.stageNotPresent(stage)) throw new IllegalStateException("No stage exists at that index!");

        Deque<Stage> stages = new ArrayDeque<>();
        stages.add(template.getStages().get(stage));

        Tutorial tutorial = new CommonTutorial(owner,new ArrayDeque<>(stages),this);

        map.put(owner, tutorial);

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
