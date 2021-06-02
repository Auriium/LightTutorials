package xyz.auriium.opentutorial.stage.response;

import xyz.auriium.opentutorial.PluginCommand;
import xyz.auriium.opentutorial.PluginScheduler;
import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.centralized.config.tutorials.Interpret;
import xyz.auriium.opentutorial.centralized.server.UUIDRegistry;
import xyz.auriium.opentutorial.stage.await.AbstractDelayConsumer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Optional;

public class AgeStageConsumer extends AbstractDelayConsumer<AgeStage, DelegateChatEvent> {

    private final UUIDRegistry registry;
    private final PluginCommand dispatcher;

    public AgeStageConsumer(PluginScheduler scheduler, UUIDRegistry registry, PluginCommand dispatcher) {
        super(scheduler);

        this.registry = registry;
        this.dispatcher = dispatcher;
    }

    @Override
    public Class<AgeStage> stageClass() {
        return AgeStage.class;
    }

    @Override
    public void consume(AgeStage stage, DelegateChatEvent event, Tutorial tutorial) {
        String message = event.getMessage().replaceAll("\\D+","");
        Optional<Player> sender = registry.getPlayer(tutorial.getIdentifier());

        try {
            int age = Integer.parseInt(message);

            sender.ifPresent(player -> {
                if (age < stage.getBelowAge()) {
                    Interpret.ifStringPresent(stage.getRunOnFail(),cmd -> {
                        dispatcher.runCommand(cmd.replaceAll("%PLAYER%",player.getName()));
                    });

                    if (stage.isCancelOnFail()) {
                        tutorial.fireCancel();
                    }
                }
            });

        } catch (NumberFormatException e) {
            sender.ifPresent(player -> {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',stage.getNotNumberMessage()));
            });
        }


        tutorial.fireNext();


    }

    @Override
    public Class<DelegateChatEvent> eventClass() {
        return DelegateChatEvent.class;
    }
}
