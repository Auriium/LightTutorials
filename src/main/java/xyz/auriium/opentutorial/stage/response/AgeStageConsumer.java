package xyz.auriium.opentutorial.stage.response;

import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.centralized.config.tutorials.Interpret;
import xyz.auriium.opentutorial.stage.await.AbstractDelayConsumer;
import org.bukkit.ChatColor;
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
        String message = event.getMessage().replaceAll("\\D+","");
        Player sender = plugin.getServer().getPlayer(tutorial.getIdentifier());

        try {
            int age = Integer.parseInt(message);

            if (sender != null && age < stage.getBelowAge()) {

                Interpret.ifStringPresent(stage.getRunOnFail(),cmd -> {
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),cmd.replaceAll("%PLAYER%",sender.getName()));
                });

                if (stage.isCancelOnFail()) {
                    tutorial.fireCancel();
                    return;
                }


            }

        } catch (NumberFormatException e) {
            if (sender != null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',stage.getNotNumberMessage()));
            }
        }


        tutorial.fireNext();


    }

    @Override
    public Class<DelegateChatEvent> eventClass() {
        return DelegateChatEvent.class;
    }
}
