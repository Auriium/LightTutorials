package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.beetle.utility.aspect.UUIDCloseable;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.event.Event;
import xyz.auriium.opentutorial.core.event.hook.HookRegistry;
import xyz.auriium.opentutorial.core.event.hook.PrecompletedHookInsertion;
import xyz.auriium.opentutorial.core.insertion.InsertionRegistry;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.tutorial.stage.IAwaitConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Initialized consumer centralizer for handling the passing of stages to different consumers
 */
public interface ConsumerCentralizer extends UUIDCloseable {

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
     * @param configController config controller
     * @return a new common consumer centralizer
     */
    static <E extends Stage,T extends Event> ConsumerCentralizer load(Platform<?> platform, InsertionRegistry registry, HookRegistry hookRegistry, ConfigController configController) {
        Map<Class<? extends Stage>, StageConsumer<? extends Stage>> map = new HashMap<>();

        registry.getAllInsertions().forEach(insertion -> {
            StageConsumer<?> consumer = insertion.build(platform, configController);

            map.put(consumer.stageClass(),consumer);

            if (consumer instanceof IAwaitConsumer) {
                IAwaitConsumer<E,T> coomer = (IAwaitConsumer<E, T>) consumer;

                hookRegistry.addHook(new PrecompletedHookInsertion(coomer));
            }
        });

        return new CommonConsumerCentralizer(map);
    }

}
