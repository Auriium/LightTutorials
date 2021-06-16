package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.beetle.utility.aspect.KeyCloseable;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.event.hook.HookRegistry;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.impl.CommonConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Initialized consumer centralizer for handling the passing of stages to different consumers
 */
public interface ConsumerCentralizer extends KeyCloseable<UUID> {

    /**
     * Gets a consumer based on a stage
     * @param stage the stage to look for
     * @param <T> stage type
     * @return the corresponding stage consumer if present
     */
    <T extends Stage> Optional<StageConsumer<T>> getConsumer(T stage);

    /**
     * Loads a consumer centralizer from resources
     * @param platform platform
     * @param registry registry
     * @param hookRegistry hook reg
     * @param configController config controller
     * @return a new common consumer centralizer
     */
    static ConsumerCentralizer load(Platform platform, ConsumerRegistry registry, HookRegistry hookRegistry, ConfigController configController) {
        Map<Class<? extends Stage>, StageConsumer<? extends Stage>> map = new HashMap<>();

        registry.getInsertions().forEach(insertion -> {
            StageConsumer<?> consumer = insertion.build(platform, configController);

            map.put(consumer.stageClass(),consumer);
        });

        return new CommonConsumerCentralizer(map, hookRegistry);
    }

}
