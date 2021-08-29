package xyz.auriium.opentutorial.spigot.stage.player;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HoloRunnable extends BukkitRunnable {

    private int delay;
    private final Hologram hologram;
    private final Player player;
    private final boolean follow;

    public HoloRunnable(int delay, Hologram hologram, Player player, boolean follow) {
        this.delay = delay;
        this.hologram = hologram;
        this.player = player;
        this.follow = follow;
    }

    @Override
    public void run() {
        delay--;

        if (delay <= 0) {
            cancel();
            hologram.delete();
            return;
        }

        if (follow) {
            hologram.teleport(player.getEyeLocation().add(player.getLocation().getDirection()));
        }


    }

}
