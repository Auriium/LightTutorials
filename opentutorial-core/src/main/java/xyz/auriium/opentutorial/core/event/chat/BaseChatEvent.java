package xyz.auriium.opentutorial.core.event.chat;

import xyz.auriium.opentutorial.core.platform.base.Audience;

import java.util.UUID;

public class BaseChatEvent {

    private final UUID id;
    private final Audience player;
    private final String message;

    public BaseChatEvent(UUID id, Audience player, String message) {
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

    public Audience getPlayer() {
        return player;
    }





}
