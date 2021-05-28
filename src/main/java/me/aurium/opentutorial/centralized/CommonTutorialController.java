package me.aurium.opentutorial.centralized;

import me.aurium.opentutorial.centralized.registry.ConsumerRegistry;
import me.aurium.opentutorial.centralized.states.StateMap;
import me.aurium.opentutorial.centralized.template.Template;

import java.util.*;

/**
 * Controller as well as entry point for closeables
 */
public class CommonTutorialController implements TutorialController {


    private final Map<UUID, Tutorial> map;

    private final ConsumerRegistry registry; //Prebuilt registry
    private final StateMap stateMap;

    public CommonTutorialController(ConsumerRegistry registry, StateMap stateMap) {
        this.map = new HashMap<>();

        this.stateMap = stateMap;
        this.registry = registry;
    }

    @Override
    public Optional<Tutorial> cancelByUUID(UUID uuid) {
        registry.closeSingle(uuid);
        stateMap.closeSingle(uuid);

        return Optional.ofNullable(map.remove(uuid));
    }

    @Override
    public boolean isInTutorial(UUID uuid) {
        return map.containsKey(uuid);
    }

    @Override
    public ConsumerRegistry getRegistry() {
        return registry;
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

        return null; /// FIXME: 5/28/2021 add implementation
    }

    @Override
    public void closeSingle(UUID uuid) {
        cancelByUUID(uuid);
    }

    @Override
    public void close() {
        registry.close();
        stateMap.close();
        map.clear();
    }
}
