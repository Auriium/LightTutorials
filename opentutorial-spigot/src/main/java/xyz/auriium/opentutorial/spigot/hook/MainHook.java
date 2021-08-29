package xyz.auriium.opentutorial.spigot.hook;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.PlatformHook;
import xyz.auriium.openmineplatform.spigot.JavaPluginTelescope;

public class MainHook implements PlatformHook {
    @Override
    public void execute(Platform platform) {
        JavaPlugin plugin = platform.telescope(JavaPluginTelescope.EXCEPTIONAL);

        plugin.getServer().getPluginManager().registerEvents(new MainListener(platform), plugin);
    }

    @Override
    public String name() {
        return "main-listener-provider";
    }
}
