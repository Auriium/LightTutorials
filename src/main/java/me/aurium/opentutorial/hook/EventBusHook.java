package me.aurium.opentutorial.hook;

import me.aurium.opentutorial.centralized.registry.EventBus;
import me.aurium.opentutorial.stage.response.DelegateChatEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EventBusHook implements Listener {

    private final EventBus bus;

    public EventBusHook(EventBus bus) {
        this.bus = bus;
    }

    public void onChatEvent(AsyncPlayerChatEvent event) {
        bus.fire(new DelegateChatEvent(event),null); //TODO: Tutorial contents -
    }


}
