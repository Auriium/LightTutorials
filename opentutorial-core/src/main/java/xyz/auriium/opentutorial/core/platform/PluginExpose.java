package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.config.templates.SerializerRegistry;
import xyz.auriium.opentutorial.core.event.hook.HookRegistry;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;

/**
 * Base (Not reloadable) construct exposer
 */
public interface PluginExpose {

    HookRegistry getHookRegistry();
    ConsumerRegistry getConsumerRegistry();
    SerializerRegistry getSerializerRegistry();

}
