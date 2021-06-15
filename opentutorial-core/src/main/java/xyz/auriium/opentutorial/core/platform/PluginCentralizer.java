package xyz.auriium.opentutorial.core.platform;

import xyz.auriium.opentutorial.core.config.templates.SerializerRegistry;
import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.platform.base.Reloadable;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.platform.impl.PluginReloader;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;

public class PluginCentralizer implements PluginExpose, Reloadable {

    private final Platform platform;

    public PluginCentralizer(Platform platform) {
        this.platform = platform;

        new PluginReloader(platform.)
    }

    @Override
    public void load() {

    }

    @Override
    public void reload() {

    }
}
