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
    public Tutorial createNew(Template template) {
       Queue<Stage> queue = new ArrayDeque<>(template.getStages());
       String templateType = template.getIdentifier();
       UUID uuid = UUID.randomUUID();

       Tutorial tutorial = new CommonTutorial(templateType,uuid,queue,this);

       map.put(uuid, tutorial);

       return tutorial;
    }

}
