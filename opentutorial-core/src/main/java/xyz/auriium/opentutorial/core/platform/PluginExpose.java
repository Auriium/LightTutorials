package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.config.templates.SerializerRegistry;
import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.tutorial.ConsumerCentralizer;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;

public interface PluginExpose {

    InnerEventBus getEventBus();
    ConsumerRegistry getConsumerRegistry();
    SerializerRegistry getSerializerRegistry();

}
