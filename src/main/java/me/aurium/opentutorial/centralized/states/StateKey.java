package me.aurium.opentutorial.centralized.states;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public interface StateKey<T extends State> {

    T create(UUID user, JavaPlugin plugin);

}
