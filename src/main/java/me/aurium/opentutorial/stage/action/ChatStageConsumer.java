package me.aurium.opentutorial.stage.action;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.centralized.config.Interpret;
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
            String title = options.getTitle();
            String subtitle = options.getSubtitle();

            Interpret.ifStringPresent(options.getChat(), message -> player.sendMessage(ChatColor.translateAlternateColorCodes('&',message)));
            Interpret.ifStringPresent(options.getActionbar(), message -> {}); /// TODO: 5/23/2021 implement actionbars

            player.sendTitle(
                    !title.equals(Interpret.NO_STRING) ? title : "",
                    !subtitle.equals(Interpret.NO_STRING) ? subtitle : "",
                    0,10,0);
        }



        continuable.fireNext();
    }

    @Override
    public Class<ChatStage> stageClass() {
        return ChatStage.class;
    }
}
