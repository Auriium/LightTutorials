package xyz.auriium.opentutorial.core.platform.impl;

import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.platform.base.Colorer;
import xyz.auriium.opentutorial.core.platform.base.PlatformDetail;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.UserRegistry;
import xyz.auriium.opentutorial.core.stage.lock.Lockable;

import java.nio.file.Path;

public class CommonPlatform<T> implements Platform<T> {

    private final ConfigExceptionHandler handler;
    private final Scheduler scheduler;
    private final Path configPath;
    private final Colorer colorer;
    private final UserRegistry<T> userRegistry;
    private final Lockable lockable;

    public CommonPlatform(ConfigExceptionHandler handler, Scheduler scheduler, Path configPath, Colorer colorer, UserRegistry<T> userRegistry, Lockable lockable) {
        this.handler = handler;
        this.scheduler = scheduler;
        this.configPath = configPath;
        this.colorer = colorer;
        this.userRegistry = userRegistry;
        this.lockable = lockable;
    }


    @Override
    public ConfigExceptionHandler exceptionHandler() {
        return handler;
    }

    @Override
    public Scheduler scheduler() {
        return scheduler;
    }

    @Override
    public PlatformDetail platformDetail() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Path configPath() {
        return configPath;
    }

    @Override
    public Colorer colorer() {
        return colorer;
    }

    @Override
    public UserRegistry<T> userRegistry() {
        return userRegistry;
    }

    @Override
    public Lockable lockable() {
        return lockable;
    }
}
