package me.aurium.opentutorial.stage.command;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.BasicStageConsumer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandStageConsumer implements BasicStageConsumer<CommandStage> {

    private final JavaPlugin plugin;

    public CommandStageConsumer(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void started(CommandStage options, Tutorial continuable) {
        options.getRunAsConsole().ifPresent(toRun -> plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), toRun));
        options.getRunAsPlayer().ifPresent(toRun -> {
            Player player = plugin.getServer().getPlayer(continuable.getIdentifier());

            if (player != null) {
                plugin.getServer().dispatchCommand(player, toRun.replaceAll("%PLAYER%", player.getName()));
            }

        });

        continuable.fireNext();
    }

    @Override
    public Class<CommandStage> stageClass() {
        return CommandStage.class;
    }
}
