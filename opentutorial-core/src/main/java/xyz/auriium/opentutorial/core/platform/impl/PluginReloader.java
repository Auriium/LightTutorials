package xyz.auriium.opentutorial.core.platform.impl;

import xyz.auriium.opentutorial.core.config.templates.SerializerRegistry;
import xyz.auriium.opentutorial.core.config.templates.impl.CommonSerializerRegistry;
import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.platform.PluginExpose;
import xyz.auriium.opentutorial.core.platform.base.Loadable;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.platform.impl.PlatformDependentModule;
import xyz.auriium.opentutorial.core.tutorial.CommonConsumerRegistry;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;

public class PluginReloader implements Loadable, PluginExpose {

    private final Platform platform;
    private final SerializerRegistry serializerRegistry;
    private final ConsumerRegistry consumerRegistry;
    private final InnerEventBus eventBus;

    private volatile PlatformDependentModule module;

    public PluginReloader(Platform platform, SerializerRegistry serializerRegistry, ConsumerRegistry consumerRegistry, InnerEventBus eventBus) {
        this.platform = platform;
        this.serializerRegistry = serializerRegistry;
        this.consumerRegistry = consumerRegistry;
        this.eventBus = eventBus;
    }

    @Override
    public void load() {
        if (module != null) {
            module.close();
        }

        module = PlatformDependentModule.load(platform,serializerRegistry,consumerRegistry,eventBus);
    }

    public PlatformDependentModule getModule() {
        return module;
    }


    @Override
    public InnerEventBus getEventBus() {
        return eventBus;
    }

    @Override
    public ConsumerRegistry getConsumerRegistry() {
        return consumerRegistry;
    }

    @Override
    public SerializerRegistry getSerializerRegistry() {
        return serializerRegistry;
    }

    public static PluginReloader launch(Platform platform) {
        return new PluginReloader(platform, CommonSerializerRegistry.defaults(), CommonConsumerRegistry.defaults(), )
    }
}
