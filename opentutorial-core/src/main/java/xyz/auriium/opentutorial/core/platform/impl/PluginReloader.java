package xyz.auriium.opentutorial.core.platform.impl;

import xyz.auriium.opentutorial.core.config.templates.SerializerRegistry;
import xyz.auriium.opentutorial.core.config.templates.impl.CommonSerializerRegistry;
import xyz.auriium.opentutorial.core.event.CommonInnerEventBus;
import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.event.hook.CommonHookRegistry;
import xyz.auriium.opentutorial.core.event.hook.HookRegistry;
import xyz.auriium.opentutorial.core.platform.PluginExpose;
import xyz.auriium.opentutorial.core.platform.base.Loadable;
import xyz.auriium.opentutorial.core.tutorial.CommonConsumerRegistry;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;

public class PluginReloader implements Loadable, PluginExpose {

    private final Platform platform;
    private final SerializerRegistry serializerRegistry;
    private final ConsumerRegistry consumerRegistry;
    private final HookRegistry hookRegistry;

    private volatile PlatformDependentModule module;

    public PluginReloader(Platform platform, SerializerRegistry serializerRegistry, ConsumerRegistry consumerRegistry, HookRegistry hookRegistry) {
        this.platform = platform;
        this.serializerRegistry = serializerRegistry;
        this.consumerRegistry = consumerRegistry;
        this.hookRegistry = hookRegistry;
    }

    @Override
    public void load() {
        if (module != null) {
            module.close();
        }

        module = PlatformDependentModule.load(platform,serializerRegistry,consumerRegistry,hookRegistry);
    }

    public PlatformDependentModule getModule() {
        return module;
    }


    @Override
    public HookRegistry getHookRegistry() {
        return hookRegistry;
    }

    @Override
    public ConsumerRegistry getConsumerRegistry() {
        return consumerRegistry;
    }

    @Override
    public SerializerRegistry getSerializerRegistry() {
        return serializerRegistry;
    }

    /**
     * Return a new reloader with all defaults but waits to launch it
     * @param platform the platform
     * @return reloader
     */
    public static PluginReloader build(Platform platform) {
        return new PluginReloader(platform, CommonSerializerRegistry.defaults(), CommonConsumerRegistry.defaults(), CommonHookRegistry.defaults());
    }
}
