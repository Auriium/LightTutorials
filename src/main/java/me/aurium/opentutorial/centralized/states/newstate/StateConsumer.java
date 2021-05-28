package me.aurium.opentutorial.centralized.states.newstate;

import me.aurium.opentutorial.centralized.states.State;
import me.aurium.opentutorial.centralized.states.StateKey;

public interface StateConsumer<T extends StateKey> {

    /**
     * What happens on insertion
     * @param state
     */
    void onInsertion(State state);

}
