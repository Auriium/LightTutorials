package xyz.auriium.opentutorial.spigot.hook;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.opentutorial.core.MissingServiceSupplier;
import xyz.auriium.opentutorial.core.PlatformDependentModule;
import xyz.auriium.opentutorial.core.config.GeneralConfig;
import xyz.auriium.opentutorial.core.types.keyword.PlatformlessChatEvent;

public class MainListener implements Listener {

    private final Platform platform;

    public MainListener(Platform platform) {
        this.platform = platform;
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent chatEvent) {
        PlatformDependentModule module = platform.serviceRegistry()
                .retrieve(PlatformDependentModule.class)
                .orElseThrow(new MissingServiceSupplier("plugin-core"));


        module.eventBus().fire(new PlatformlessChatEvent(chatEvent.getPlayer().getUniqueId(), chatEvent.getMessage()));

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        PlatformDependentModule module = platform.serviceRegistry()
                .retrieve(PlatformDependentModule.class)
                .orElseThrow(new MissingServiceSupplier("plugin-core"));

        module.closeSingle(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PlatformDependentModule module = platform.serviceRegistry()
                .retrieve(PlatformDependentModule.class)
                .orElseThrow(new MissingServiceSupplier("plugin-core"));

        GeneralConfig config = module.configController().getGeneralConfig();

        String defaultTut = config.defaultTutorial();
        Player player = event.getPlayer();

        if (!defaultTut.equals("none") && player.hasPermission(config.defaultPermission())) {
            module.templateController().getByIdentifier(defaultTut).ifPresentOrElse(template -> {
                module.tutorialController().createNew(template,player.getUniqueId()).fireNext();
            }, () -> {
                module.configController().getMessageConfig().invalidTemplateMessage().send(platform.interRegistry().get(player.getUniqueId()),defaultTut);
            });
        }
    }
}
