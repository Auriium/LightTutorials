package xyz.auriium.opentutorial.stage.response;

import xyz.auriium.opentutorial.centralized.registry.Event;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DelegateChatEvent implements Event {


    public UUID getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    private final UUID id;
    private final Player player;
    private final String message;


    public DelegateChatEvent(UUID id, Player player, String message) {
        this.id = id;
        this.player = player;
        this.message = message;
    }
}
