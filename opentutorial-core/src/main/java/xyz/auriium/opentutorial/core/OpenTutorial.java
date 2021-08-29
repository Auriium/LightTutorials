package xyz.auriium.opentutorial.core;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePlugin;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;

public class OpenTutorial implements ReloadablePlugin {

    private final ReloadablePluginState state;
    private final Platform platform;
    private volatile PlatformDependentModule module;

    private final CustomConsumer consumer;

    public OpenTutorial(ReloadablePluginState state, Platform platform, CustomConsumer consumer) {
        this.state = state;
        this.platform = platform;
        this.consumer = consumer;
    }


    @Override
    public void onStart() {
        ConsumerRegistry registry = platform.serviceRegistry()
                .retrieve(ConsumerRegistry.class)
                .orElseThrow(() -> new IllegalStateException("Server platform has no stage registry! Something terrible has gone wrong, contact the developer of this plugin!"));

        module = CommonDependentModule.launch(platform, registry);

        platform.serviceRegistry().register(PlatformDependentModule.class, module, "plugin"); //TODO phase this out

        consumer.consume(platform, module, state);
    }

    @Override
    public void onStop() {
        module.close();
    }
}
