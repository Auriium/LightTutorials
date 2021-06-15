package xyz.auriium.opentutorial.spigot.hook;

import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.auriium.opentutorial.core.event.InnerEventBus;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.TutorialController;
import xyz.auriium.opentutorial.core.event.chat.BaseChatEvent;

import java.util.Optional;

public class EventBusListener implements Listener {

    private final InnerEventBus bus;
    private final Scheduler scheduler;
    private final TutorialController controller;

    public EventBusListener(InnerEventBus bus, Scheduler scheduler, TutorialController controller) {
        this.bus = bus;
        this.scheduler = scheduler;
        this.controller = controller;
    }


    public void onChatEvent(AsyncPlayerChatEvent event) {

        Optional<Tutorial> optionalTutorial = controller.getByUUID(event.getPlayer().getUniqueId());

        scheduler.runLater(() -> { /// FIXME: 5/31/2021 possibly make receiving tutorials threadsafe to allow async message w/o scheduler shit
            optionalTutorial.ifPresent(tutorial -> bus.fire(new BaseChatEvent(event.getPlayer().getUniqueId(),event.getPlayer(),event.getMessage()),tutorial));
        }, 0L);



    }


}
