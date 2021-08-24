package xyz.auriium.opentutorial.core.platform.impl;

import xyz.auriium.beetle.utility.aspect.UUIDCloseable;
import xyz.auriium.opentutorial.core.OpenTutorial;
import xyz.auriium.opentutorial.core.event.hook.CommonHookRegistry;
import xyz.auriium.opentutorial.core.event.hook.HookRegistry;
import xyz.auriium.opentutorial.core.insertion.CommonInsertionRegistry;
import xyz.auriium.opentutorial.core.insertion.InsertionRegistry;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.platform.PlatformDependentModule;
import xyz.auriium.opentutorial.core.platform.PluginExpose;
import xyz.auriium.opentutorial.core.platform.Loadable;

import java.util.UUID;

/**
 * Typical loader implementation
 */
public class PlatformDependentLoader<T> implements Loadable, PluginExpose, UUIDCloseable {

    private final Platform<T> platform;
    private final InsertionRegistry insertionRegistry;
    private final HookRegistry hookRegistry;

    private volatile PlatformDependentModule module;

    public PlatformDependentLoader(Platform<T> platform, InsertionRegistry insertionRegistry, HookRegistry hookRegistry) {
        this.platform = platform;
        this.insertionRegistry = insertionRegistry;
        this.hookRegistry = hookRegistry;
    }


    @Override
    public void load() {
        if (module != null) {
            module.close();
        }

        module = PlatformDependentModule.load(platform,insertionRegistry,hookRegistry);
    }

    public OpenTutorial getOT() {
        return null;
    }

    public PlatformDependentModule getModule() {
        return module;
    }


    @Override
    public HookRegistry getHookRegistry() {
        return hookRegistry;
    }

    @Override
    public InsertionRegistry getInsertionRegistry() {
        return insertionRegistry;
    }

    /**
     * Return a new reloader with all defaults but waits to launch it
     * @param platform the platform
     * @return reloader
     */
    public static <T> PlatformDependentLoader<T> build(Platform<T> platform) {
        return new PlatformDependentLoader<>(platform, CommonInsertionRegistry.defaults(), CommonHookRegistry.defaults());
    }

    @Override
    public void closeSingle(UUID uuid) {
        if (module != null) {
            module.closeSingle(uuid);
        }
    }

    @Override
    public void close() {
        if (module != null) {
            module.close();
        }
    }
}
