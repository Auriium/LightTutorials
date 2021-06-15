package xyz.auriium.opentutorial.core.event.chat;

import xyz.auriium.opentutorial.core.platform.base.Teachable;

import java.util.UUID;

public class BaseChatEvent {

    private final UUID id;
    private final Teachable player;
    private final String message;

    public BaseChatEvent(UUID id, Teachable player, String message) {
        this.id = id;
        this.player = player;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Teachable getPlayer() {
        return player;
    }





}
