package xyz.auriium.opentutorial.spigot;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.auriium.opentutorial.core.config.GeneralConfig;
import xyz.auriium.opentutorial.core.event.chat.PlatformlessChatEvent;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.platform.impl.PlatformDependentLoader;

public class SpigotBusHook implements Listener {

    private final Platform<Player> platform;
    private final PlatformDependentLoader<Player> loader;

    public SpigotBusHook(Platform<Player> platform, PlatformDependentLoader<Player> loader) {
        this.platform = platform;
        this.loader = loader;
    }


    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent chatEvent) {
        loader.getModule().eventBus().fire(new PlatformlessChatEvent(platform.userRegistry().wrapUser(chatEvent.getPlayer()), chatEvent.getMessage()));

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
                loader.getModule().configController().getMessageConfig().invalidTemplateMessage().send(platform.userRegistry().wrapUser(player),defaultTut);
            });
        }
    }


}
