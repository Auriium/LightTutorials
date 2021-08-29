package xyz.auriium.opentutorial.spigot.hook;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.ServiceHook;
import xyz.auriium.openmineplatform.spigot.JavaPluginTelescope;
import xyz.auriium.opentutorial.core.platform.UserChatSuppressor;

public class SuppressorHook implements ServiceHook<UserChatSuppressor> {

    @Override
    public Class<UserChatSuppressor> providedServiceType() {
        return UserChatSuppressor.class;
    }

    @Override
    public UserChatSuppressor provide(Platform platform) {
        JavaPlugin plugin = platform.telescope(JavaPluginTelescope.EXCEPTIONAL);

        ChatListener listener = new ChatListener();

        plugin.getServer()
                .getPluginManager()
                .registerEvents(listener, plugin);

        return listener;
    }

    @Override
    public String name() {
        return "suppressor-listener-provider";
    }
}
