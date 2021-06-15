package xyz.auriium.opentutorial.spigot.platform;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.config.impl.WarningExceptionHandler;
import xyz.auriium.opentutorial.core.event.CommonInnerEventBus;
import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.platform.impl.CommonPlatform;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.platform.PlatformLauncher;
import xyz.auriium.opentutorial.core.platform.base.Colorer;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.UserRegistry;

import java.nio.file.Path;

public class SpigotPlatformLauncher implements PlatformLauncher {

    private final JavaPlugin plugin;

    public SpigotPlatformLauncher(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Platform launch() {

        UserRegistry<?> userRegistry = new SpigotTeachableRegistry(plugin.getServer());
        InnerEventBus innerEventBus = new CommonInnerEventBus();
        Scheduler scheduler = new SpigotScheduler(plugin);
        Path configPath = plugin.getDataFolder().toPath();
        Colorer colorer = new SpigotColorer();
        ConfigExceptionHandler handler = new WarningExceptionHandler(userRegistry);

        return new CommonPlatform(handler,innerEventBus,scheduler,configPath,colorer,userRegistry);
    }
}
