package me.aurium.opentutorial.hook;

import me.aurium.opentutorial.centralized.TutorialController;
import me.aurium.opentutorial.centralized.config.GeneralConfig;
import me.aurium.opentutorial.centralized.template.TemplateController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class StartupHook implements Listener {

    private final TutorialController controller;
    private final TemplateController templateController;

    private final GeneralConfig config;

    public StartupHook(TutorialController controller, TemplateController templateController, GeneralConfig config) {
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
            templateController.loadTemplate(config.defaultTutorial()).ifPresentOrElse(template -> {

                controller.createNew(template,newbie).fireNext(); //START!!!!!!

            }, () -> {
                /// FIXME: 5/21/2021 log to slf4j first an exception and then the pretty error
            });
        }
    }

}
