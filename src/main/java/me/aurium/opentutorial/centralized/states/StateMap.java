package me.aurium.opentutorial.centralized.states;

import me.aurium.beetle.defaults.utility.aspect.UUIDCloseable;
import me.aurium.beetle.defaults.utility.map.optional.DelegatingOptionalMap;
import me.aurium.beetle.defaults.utility.map.optional.OptionalMap;
import me.aurium.opentutorial.PluginScheduler;
import me.aurium.opentutorial.centralized.server.UUIDRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StateMap implements UUIDCloseable {

    private final OptionalMap<UUID,Map<StateKey<?>, State>> newMap = new DelegatingOptionalMap<>();

    private final UUIDRegistry server; //TODO interface this away!
    private final PluginScheduler scheduler;

    public StateMap(UUIDRegistry server, PluginScheduler scheduler) {
        this.server = server;
        this.scheduler = scheduler;
    }

    public <T extends State> void startState(UUID player, StateKey<T> key) {
        Map<StateKey<?>,State> minimap = newMap.delegate().computeIfAbsent(player, s -> new HashMap<>());

        minimap.computeIfAbsent(key, s -> key.create(scheduler,new CommonWeakPlayer(server,player)));
    }



    @SuppressWarnings("unchecked")
    public <T extends State> T getState(UUID player, StateKey<T> key) {
        //TODO checking and other shit - this is sloppy
        return (T) map.computeIfAbsent(player, s -> new HashMap<>()).computeIfAbsent(key, s ->
                key.create(scheduler, new CommonWeakPlayer(server,player))
        );
    }

    public void closeSingle(UUID uuid) {
        newMap.remove(uuid).ifPresent(map -> map.values().forEach(State::deactivate));
    }


    public void close() {
        newMap.delegate().values().forEach(map -> map.values().forEach(State::deactivate));
        map.clear();
    }
}
