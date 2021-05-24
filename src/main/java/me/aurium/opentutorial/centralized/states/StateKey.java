package me.aurium.opentutorial.centralized.states;

import me.aurium.opentutorial.PluginScheduler;

public interface StateKey<T extends State> {

    T create(PluginScheduler scheduler, WeakPlayer player);

}
