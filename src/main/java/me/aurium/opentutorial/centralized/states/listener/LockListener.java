package me.aurium.opentutorial.centralized.states.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * ugly ugly ugly shit - it is what it is.
 *
 * Still a disgusting class
 */
public class LockListener implements Listener {

    private final Set<UUID> lockMovement = new HashSet<>();
    private final Set<UUID> lockView = new HashSet<>();

    //probably can just encapsulate properly and only provide add/remove access
    public Set<UUID> getLockMovement() {
        return lockMovement;
    }

    public Set<UUID> getLockView() {
        return lockView;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        UUID player = event.getPlayer().getUniqueId();

        Location from = event.getFrom();
        Location to = event.getTo();

        if (to != null) {

            if(lockMovement.contains(player)){
                if(from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ()){
                    event.setCancelled(true);
                }
            }

            if(lockView.contains(player)){
                if(from.getYaw() != to.getYaw() || from.getPitch() != to.getPitch()){
                    event.setCancelled(true);
                }
            }
        }


    }

}
