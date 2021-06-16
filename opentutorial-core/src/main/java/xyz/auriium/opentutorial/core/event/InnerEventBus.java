package xyz.auriium.opentutorial.core.event;

import xyz.auriium.beetle.utility.map.multi.DelegatingMultiMap;
import xyz.auriium.beetle.utility.map.multi.MultiMap;
import xyz.auriium.opentutorial.core.config.ConfigController;
import xyz.auriium.opentutorial.core.event.hook.HookInsertion;
import xyz.auriium.opentutorial.core.event.hook.HookRegistry;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;

import java.util.UUID;

/**
 * Inner event bus backbone
 */
public interface InnerEventBus {


    <E extends Event> void fire(E event, Tutorial tutorial);

    <E extends AssociatedEvent> void fire(E event);
    <E extends Event> void fire(E event, UUID tutorial);

    static InnerEventBus load(Platform platform, HookRegistry hookRegistry, TutorialController tutorialController, ConfigController configController) {
        MultiMap<Class<?>,InnerEventConsumer<?>> map = new DelegatingMultiMap<>();

        for (HookInsertion insertion : hookRegistry.getInsertions()) {
            InnerEventConsumer<?> consumer = insertion.build(platform,tutorialController,configController);

            map.insert(consumer.eventClass(),consumer);
        }

        return new CommonInnerEventBus(map, tutorialController);
    }


}
