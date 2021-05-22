package me.aurium.opentutorial.stage.chatstage;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.BasicStageConsumer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatStageConsumer implements BasicStageConsumer<ChatStage> {

    private final JavaPlugin plugin;

    public ChatStageConsumer(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void started(ChatStage options, Tutorial continuable) {
        Player player = plugin.getServer().getPlayer(continuable.getIdentifier());

        if (player != null) {
            options.getChat().ifPresent(message -> player.sendMessage(ChatColor.translateAlternateColorCodes('&',message)));

            options.getActionbar().ifPresent(message -> {
                /// TODO: 5/22/2021 implement
            });

            player.sendTitle(options.getTitle() != null ? options.getTitle() : "", options.getSubtitle() != null ? options.getSubtitle() : "",
                    0,10,0);
        }



        continuable.fireNext();
    }

    @Override
    public Class<ChatStage> stageClass() {
        return ChatStage.class;
    }
}
