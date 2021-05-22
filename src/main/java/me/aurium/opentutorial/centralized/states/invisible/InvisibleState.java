package me.aurium.opentutorial.centralized.states.invisible;

import me.aurium.opentutorial.centralized.states.State;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class InvisibleState implements State {

    private final static PotionEffect effect = new PotionEffect(PotionEffectType.INVISIBILITY,Integer.MAX_VALUE,1);

    private final JavaPlugin plugin;

    public InvisibleState(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void closeSingle(UUID uuid) {
        Player p = plugin.getServer().getPlayer(uuid);

        if (p != null) {
            p.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
    }

    @Override
    public void closeAll() {
        //TODO: i don't know how to handle this here
    }

    @Override
    public void activate(UUID player) {
        Player p = plugin.getServer().getPlayer(player);

        if (p != null) {
            p.addPotionEffect(effect);
        }
    }

    @Override
    public void deactivate(UUID player) {
        closeSingle(player);
    }
}
