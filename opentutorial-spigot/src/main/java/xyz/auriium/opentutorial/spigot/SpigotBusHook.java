package xyz.auriium.opentutorial.spigot;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.auriium.opentutorial.core.config.GeneralConfig;
import xyz.auriium.opentutorial.core.event.chat.PlatformlessChatEvent;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.platform.impl.PlatformDependentLoader;
import xyz.auriium.opentutorial.spigot.platform.SpigotTeachable;

public class SpigotBusHook implements Listener {

    private final PlatformDependentLoader loader;

    public SpigotBusHook(PlatformDependentLoader loader) {
        this.loader = loader;
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent chatEvent) {

        loader.getModule().eventBus().fire(new PlatformlessChatEvent(new SpigotTeachable(chatEvent.getPlayer()),chatEvent.getMessage()));
        //TODO cancel
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        loader.closeSingle(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        GeneralConfig config = loader.getModule().configController().getGeneralConfig();

        String defaultTut = config.defaultTutorial();
        Player player = event.getPlayer();

        if (!defaultTut.equals("none") && player.hasPermission(config.defaultPermission())) {
            loader.getModule().templateController().getByIdentifier(defaultTut).ifPresentOrElse(template -> {
                loader.getModule().tutorialController().createNew(template,player.getUniqueId()).fireNext();
            }, () -> {
                loader.getModule().configController().getMessageConfig().invalidTemplateMessage().send(new SpigotTeachable(player),defaultTut);
            });
        }
    }


}
