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
import xyz.auriium.opentutorial.core.template.Template;
import xyz.auriium.opentutorial.core.types.keyword.PlatformlessChatEvent;
import xyz.auriium.opentutorial.spigot.PlayerConsumer;

import java.util.Optional;

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
            Optional<Template> template = module.templateController().getByIdentifier(defaultTut);

            if (template.isPresent()) {
                module.tutorialController().createNew(template.get(), player.getUniqueId()).fireNext();
                return;
            }

            module.configController().getMessageConfig().invalidTemplateMessage().send(new PlayerConsumer(player),defaultTut);
        }
    }
}
