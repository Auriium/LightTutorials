package xyz.auriium.opentutorial.spigot.hook;

import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xyz.auriium.opentutorial.core.platform.impl.PlatformDependentLoader;
import xyz.auriium.opentutorial.core.tutorial.stage.killbirds.KillABirdEvent;

public class KillabirdHook implements Listener {

    private final PlatformDependentLoader<Player> loader;

    public KillabirdHook(PlatformDependentLoader<Player> loader) {
        this.loader = loader;
    }

    @EventHandler
    public void onListener(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof Parrot) {
            loader.getModule().eventBus().fire(new KillABirdEvent(), event.getDamager().getUniqueId());
        }


    }
}
