package me.aurium.opentutorial.stage.response;

import me.aurium.opentutorial.centralized.event.Event;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class DelegateChatEvent implements Event {

    private final AsyncPlayerChatEvent event;

    public DelegateChatEvent(AsyncPlayerChatEvent event) {
        this.event = event;
    }

    public AsyncPlayerChatEvent getEvent() {
        return event;
    }

}
