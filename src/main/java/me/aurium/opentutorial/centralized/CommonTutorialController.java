package me.aurium.opentutorial.centralized;

import me.aurium.opentutorial.centralized.registry.ConsumerRegistry;
import me.aurium.opentutorial.centralized.template.Template;
import me.aurium.opentutorial.stage.Stage;

import java.util.*;

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
    public ConsumerRegistry getRegistry() {
        return registry;
    }

    @Override
    public Tutorial createNew(Template template, UUID owner) {
       Tutorial tutorial = new CommonTutorial(owner,new ArrayDeque<>(template.getStages()),this);

       map.put(owner, tutorial);

       return tutorial;
    }

    @Override
    public void closeSingle(UUID uuid) {
        cancelByUUID(uuid);
    }

    @Override
    public void closeAll() {
        registry.closeAll();
    }
}
