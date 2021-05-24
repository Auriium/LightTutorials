package me.aurium.opentutorial.centralized.states;

import me.aurium.beetle.defaults.utility.aspect.UUIDCloseable;
import me.aurium.opentutorial.PluginScheduler;
import me.aurium.opentutorial.centralized.TutorialController;
import me.aurium.opentutorial.centralized.server.UUIDRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StateMap implements UUIDCloseable {

    private final Map<UUID,Map<StateKey<?>, State>> map = new HashMap<>();

    private final UUIDRegistry server; //TODO interface this away!
    private final PluginScheduler scheduler;

    public StateMap(UUIDRegistry server, PluginScheduler scheduler) {
        this.server = server;
        this.scheduler = scheduler;
    }


    public void closeSingle(UUID uuid) {
        map.get(uuid).forEach((stateKey, state) -> state.deactivate());
        map.remove(uuid);
    }


    public void close() {
        map.forEach((uuid,map1) -> map1.forEach((key, state) -> state.deactivate()));
        map.clear();
    }


    @SuppressWarnings("unchecked")
    public <T extends State> T getState(UUID player, StateKey<T> key) {
        //TODO checking and other shit - this is sloppy
        return (T) map.computeIfAbsent(player, s -> new HashMap<>()).computeIfAbsent(key, s ->
                key.create(scheduler, new CommonWeakPlayer(server,player))
        );
    }
}
