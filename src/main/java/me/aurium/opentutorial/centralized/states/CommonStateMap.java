package me.aurium.opentutorial.centralized.states;

import me.aurium.opentutorial.aspect.UUIDCloseable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommonStateMap implements UUIDCloseable {

    private final Map<UUID,Map<StateKey<?>, State>> map = new HashMap<>();

    private final JavaPlugin plugin;

    public CommonStateMap(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void closeSingle(UUID uuid) {
        map.get(uuid).forEach((stateKey, state) -> state.deactivate());
    }


    public void closeAll() {
        map.forEach((uuid,map1) -> {
            map1.forEach((key,state) -> state.deactivate());
        });
    }


    public <T extends State> T getState(UUID player, StateKey<T> key) {
        //TODO checking and other shit - this is sloppy
        return (T) map.computeIfAbsent(player, s -> new HashMap<>()).computeIfAbsent(key, s -> key.create(player,plugin));
    }
}
