package xyz.auriium.opentutorial.spigot.hook;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.auriium.opentutorial.core.platform.UserLocker;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserLockerListener implements Listener, UserLocker {

    private final Set<UUID> lockMovement = new HashSet<>();
    private final Set<UUID> lockView = new HashSet<>();

    //probably can just encapsulate properly and only provide add/remove access
    public final Set<UUID> getLockMovement() {
        return lockMovement;
    }

    public final Set<UUID> getLockView() {
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

    @Override
    public void setLockedMovement(UUID uuid, Boolean locked) {
        if (locked) {
            lockMovement.add(uuid);
        } else {
            lockMovement.remove(uuid);
        }

    }

    @Override
    public void setLockedView(UUID uuid, Boolean locked) {
        if (locked) {
            lockView.add(uuid);
        } else {
            lockView.remove(uuid);
        }
    }

    @Override
    public void removeOne(UUID uuid) {
        lockMovement.remove(uuid);
        lockView.remove(uuid);
    }

    @Override
    public void removeAll() {
        lockView.clear();
        lockMovement.clear();
    }
}
