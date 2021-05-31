package me.aurium.opentutorial.stage.response;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.await.AbstractDelayConsumer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AgeStageConsumer extends AbstractDelayConsumer<AgeStage, DelegateChatEvent> {

    private final JavaPlugin plugin;

    public AgeStageConsumer(JavaPlugin plugin) {
        super(plugin);

        this.plugin = plugin;
    }

    @Override
    public Class<AgeStage> stageClass() {
        return AgeStage.class;
    }

    @Override
    public void consume(AgeStage stage, DelegateChatEvent event, Tutorial tutorial) {
        String message = event.getEvent().getMessage().replaceAll("\\D+","");

        int age = Integer.parseInt(message);


        Player sender = plugin.getServer().getPlayer(tutorial.getIdentifier());

        if (sender != null && age < stage.getBelowAge()) {
            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),stage.getRunOnFail().replaceAll("%PLAYER%",sender.getName()));

            if (stage.isCancelOnFail()) {
                tutorial.fireCancel();
                return;
            }
        }

        tutorial.fireNext();


    }

    @Override
    public Class<DelegateChatEvent> eventClass() {
        return DelegateChatEvent.class;
    }
}
