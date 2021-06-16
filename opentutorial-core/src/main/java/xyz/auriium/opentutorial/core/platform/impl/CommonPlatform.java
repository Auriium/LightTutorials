package xyz.auriium.opentutorial.core.platform.impl;

import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.platform.base.Colorer;
import xyz.auriium.opentutorial.core.platform.base.PlatformDetail;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.UserRegistry;

import java.nio.file.Path;

public class CommonPlatform implements Platform {

    private final ConfigExceptionHandler handler;
    private final Scheduler scheduler;
    private final Path configPath;
    private final Colorer colorer;
    private final UserRegistry<?> userRegistry;

    public CommonPlatform(ConfigExceptionHandler handler, Scheduler scheduler, Path configPath, Colorer colorer, UserRegistry<?> userRegistry) {
        this.handler = handler;
        this.scheduler = scheduler;
        this.configPath = configPath;
        this.colorer = colorer;
        this.userRegistry = userRegistry;
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
        return null;
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
    public UserRegistry<?> userRegistry() {
        return userRegistry;
    }
}
