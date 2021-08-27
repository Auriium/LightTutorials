package xyz.auriium.opentutorial.core;

import xyz.auriium.openmineplatform.api.HookData;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.PlatformProjectIdentity;
import xyz.auriium.openmineplatform.api.plugin.PluginRepresentation;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePlugin;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;

public abstract class OpenTutorialRepresentation implements PluginRepresentation {
    @Override
    public PlatformProjectIdentity getIdentity() {
        return new PlatformProjectIdentity("OpenTutorial", "Auriium", "1.0.0-SNAPSHOT");
    }

    @Override
    public ReloadablePlugin supply(Platform platform, ReloadablePluginState reloadablePluginState) {
        return new OpenTutorial(reloadablePluginState, platform, this::postStartup);
    }

    public abstract void postStartup(Platform platform, PlatformDependentModule module, ReloadablePluginState state);
}
