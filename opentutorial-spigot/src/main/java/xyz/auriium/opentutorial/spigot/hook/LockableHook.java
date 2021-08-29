package xyz.auriium.opentutorial.spigot.hook;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.ServiceHook;
import xyz.auriium.openmineplatform.spigot.JavaPluginTelescope;
import xyz.auriium.opentutorial.core.platform.UserLocker;

public class LockableHook implements ServiceHook<UserLocker> {
    @Override
    public Class<UserLocker> providedServiceType() {
        return UserLocker.class;
    }

    @Override
    public UserLocker provide(Platform platform) {
        JavaPlugin plugin = platform.telescope(JavaPluginTelescope.EXCEPTIONAL);

        UserLockerListener listener = new UserLockerListener();

        plugin.getServer()
                .getPluginManager()
                .registerEvents(listener, plugin);

        return listener;
    }

    @Override
    public String name() {
        return "lockable-listener-provider";
    }
}
