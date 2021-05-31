package me.aurium.opentutorial.centralized;

import me.aurium.opentutorial.centralized.registry.ConsumerRegistry;
import me.aurium.opentutorial.centralized.template.Template;

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
        map.clear();
    }
}
