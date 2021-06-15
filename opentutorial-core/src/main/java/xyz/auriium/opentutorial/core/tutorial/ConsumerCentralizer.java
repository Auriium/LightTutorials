package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.beetle.utility.aspect.KeyCloseable;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.tutorial.impl.CommonConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ConsumerCentralizer extends KeyCloseable<UUID> {

    <T extends Stage> Optional<StageConsumer<T>> getConsumer(T stage);

    static ConsumerCentralizer load(Platform platform, ConsumerRegistry registry, ConfigController configController) {
        Map<Class<? extends Stage>, StageConsumer<? extends Stage>> map = new HashMap<>();

        registry.getInsertions().forEach(insertion -> {
            StageConsumer<?> consumer = insertion.build(platform, configController);

            map.put(consumer.stageClass(),consumer);
        });

        return new CommonConsumerCentralizer(map, platform.eventBus());
    }

}
