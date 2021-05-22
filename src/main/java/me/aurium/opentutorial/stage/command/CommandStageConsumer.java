package me.aurium.opentutorial.stage.command;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.BasicStageConsumer;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandStageConsumer implements BasicStageConsumer<CommandStage> {

    private final JavaPlugin plugin;

    public CommandStageConsumer(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void started(CommandStage options, Tutorial continuable) {
        plugin.getServer().getConsoleSender();

        /// TODO: 5/21/2021 Run command from options, if it fails continue and log an error (not exception) to console

        continuable.fireNext();
    }

    @Override
    public Class<CommandStage> stageClass() {
        return CommandStage.class;
    }
}
