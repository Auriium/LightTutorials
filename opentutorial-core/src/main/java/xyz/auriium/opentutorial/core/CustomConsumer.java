package xyz.auriium.opentutorial.core;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;

public interface CustomConsumer {

    void consume(Platform platform, PlatformDependentModule module, ReloadablePluginState state);

}
