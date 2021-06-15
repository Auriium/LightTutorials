package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.config.templates.SerializerRegistry;
import xyz.auriium.opentutorial.core.platform.base.Loadable;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;

public class PluginCentralizer implements Loadable {

    private final Platform platform;
    private final SerializerRegistry serializerRegistry;
    private final ConsumerRegistry consumerRegistry;

    private volatile PlatformDependentModule module;

    public PluginCentralizer(Platform platform, SerializerRegistry serializerRegistry, ConsumerRegistry consumerRegistry) {
        this.platform = platform;
        this.serializerRegistry = serializerRegistry;
        this.consumerRegistry = consumerRegistry;
    }

    @Override
    public void load() {
        if (module != null) {
            module.close();
        }

        module = PlatformDependentModule.load(platform,serializerRegistry,consumerRegistry);
    }

    public PlatformDependentModule getModule() {
        return module;
    }
}
