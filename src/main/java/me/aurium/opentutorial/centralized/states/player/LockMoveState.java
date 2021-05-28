package me.aurium.opentutorial.centralized.states.player;

import me.aurium.opentutorial.centralized.states.State;
import me.aurium.opentutorial.centralized.states.WeakPlayer;

public class LockMoveState implements State {

    private final WeakPlayer player;

    public LockMoveState(WeakPlayer player) {
        this.player = player;
    }

    public void activate() {

    }

    @Override
    public void deactivate() {
        player.doPlayer(p -> {

        });
    }
}
