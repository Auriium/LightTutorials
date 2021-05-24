package me.aurium.opentutorial.centralized.states.player;

import me.aurium.opentutorial.centralized.states.State;
import me.aurium.opentutorial.centralized.states.WeakPlayer;

public class InvisibleState implements State {

    private final WeakPlayer player;

    public InvisibleState(WeakPlayer player) {
        this.player = player;
    }

    public void activate() {
        //can't see how this could go wrong
        player.doPlayer(p -> p.setInvisible(true));
    }

    @Override
    public void deactivate() {
        player.doPlayer(p -> p.setInvisible(false));
    }
}
