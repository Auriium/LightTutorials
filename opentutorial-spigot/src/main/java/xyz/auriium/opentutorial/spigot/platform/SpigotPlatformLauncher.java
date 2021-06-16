package xyz.auriium.opentutorial.spigot.platform;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.config.impl.WarningExceptionHandler;
import xyz.auriium.opentutorial.core.platform.PlatformLauncher;
import xyz.auriium.opentutorial.core.platform.base.Colorer;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.platform.base.UserRegistry;
import xyz.auriium.opentutorial.core.platform.impl.CommonPlatform;
import xyz.auriium.opentutorial.core.platform.impl.Platform;

import java.nio.file.Path;

public class SpigotPlatformLauncher implements PlatformLauncher {

    private final JavaPlugin plugin;
    private boolean hasLaunched;

    public SpigotPlatformLauncher(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Platform launch() {

        if (hasLaunched) {
            throw new IllegalStateException("Platform has already been launched for this JavaPlugin!");
        }

        UserRegistry<?> userRegistry = new SpigotTeachableRegistry(plugin.getServer());
        Scheduler scheduler = new SpigotScheduler(plugin);
        Path configPath = plugin.getDataFolder().toPath();
        Colorer colorer = new SpigotColorer();
        ConfigExceptionHandler handler = new WarningExceptionHandler(userRegistry);

        hasLaunched = true;

        return new CommonPlatform(handler, scheduler,configPath,colorer,userRegistry);
    }
}
