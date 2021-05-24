package me.aurium.opentutorial.centralized.states.player;

import me.aurium.opentutorial.PluginScheduler;
import me.aurium.opentutorial.centralized.TutorialController;
import me.aurium.opentutorial.centralized.states.StateKey;
import me.aurium.opentutorial.centralized.states.WeakPlayer;

public class InvisibleKey implements StateKey<InvisibleState> {

    InvisibleKey() {}

    public static InvisibleKey INSTANCE = new InvisibleKey();


    @Override
    public InvisibleState create(PluginScheduler scheduler, WeakPlayer player) {
        return new InvisibleState(player);
    }
}
