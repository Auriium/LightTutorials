package gg.qteam.lighttutorials.stage.listener;

import gg.qteam.lighttutorials.stage.await.IdentifiableEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class DelegateChatEvent implements IdentifiableEvent {

    private final AsyncPlayerChatEvent event;

    public DelegateChatEvent(AsyncPlayerChatEvent event) {
        this.event = event;
    }

    public AsyncPlayerChatEvent getEvent() {
        return event;
    }

    @Override
    public UUID getSpawnedIdentifier() {
        return event.getPlayer().getUniqueId();
    }
}
