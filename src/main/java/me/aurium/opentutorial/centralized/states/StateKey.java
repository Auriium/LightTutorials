package me.aurium.opentutorial.centralized.states;

import me.aurium.opentutorial.PluginScheduler;
import me.aurium.opentutorial.centralized.TutorialController;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public interface StateKey<T extends State> {

    T create(PluginScheduler scheduler, WeakPlayer player);

}
