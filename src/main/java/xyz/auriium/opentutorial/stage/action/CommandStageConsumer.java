package xyz.auriium.opentutorial.stage.action;

import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.centralized.config.tutorials.Interpret;
import xyz.auriium.opentutorial.stage.BasicStageConsumer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandStageConsumer implements BasicStageConsumer<CommandStage> {

    private final JavaPlugin plugin;

    public CommandStageConsumer(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void started(CommandStage options, Tutorial continuable) {

        Player player = plugin.getServer().getPlayer(continuable.getIdentifier());


        //cryptic unreadable shit
        Interpret.ifStringPresent(options.getRunAsConsole(), (string) -> plugin.getServer().dispatchCommand(
                plugin.getServer().getConsoleSender(), player != null ? string.replaceAll("%PLAYER%",player.getName()) : string));

        Interpret.ifStringPresent(options.getRunAsPlayer(), (string) -> {
            if (player != null) {
                plugin.getServer().dispatchCommand(player, string.replaceAll("%PLAYER%", player.getName()));
            }
        });

        continuable.fireNext();
    }

    @Override
    public Class<CommandStage> stageClass() {
        return CommandStage.class;
    }
}
