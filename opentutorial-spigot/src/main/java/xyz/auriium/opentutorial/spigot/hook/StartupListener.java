package xyz.auriium.opentutorial.spigot.hook;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.auriium.opentutorial.core.config.types.general.GeneralConfig;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.tutorial.template.TemplateController;

import java.util.UUID;

public class StartupListener implements Listener {

    private final TutorialController controller;
    private final TemplateController templateController;

    private final GeneralConfig config;

    public StartupListener(TutorialController controller, TemplateController templateController, GeneralConfig config) {
        this.controller = controller;
        this.templateController = templateController;
        this.config = config;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        controller.cancelByUUID(event.getPlayer().getUniqueId());
    }

    public void onJoin(PlayerJoinEvent event) {
        UUID newbie = event.getPlayer().getUniqueId();

        if (config.defaultEnabled()) {
            templateController.getByIdentifier(config.defaultTutorial()).ifPresentOrElse(template -> {

                controller.createNew(template,newbie).fireNext(); //START!!!!!!

            }, () -> {
                /// FIXME: 5/21/2021 log to slf4j first an exception and then the pretty error

                throw new IllegalStateException("cum");
            });
        }
    }

}
