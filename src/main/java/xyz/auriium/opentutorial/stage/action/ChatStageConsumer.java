package xyz.auriium.opentutorial.stage.action;

import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.centralized.config.tutorials.Interpret;
import xyz.auriium.opentutorial.centralized.server.UUIDRegistry;
import xyz.auriium.opentutorial.stage.BasicStageConsumer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatStageConsumer implements BasicStageConsumer<ChatStage> {

    private final UUIDRegistry registry;

    public ChatStageConsumer(UUIDRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void started(ChatStage options, Tutorial continuable) {
        registry.getPlayer(continuable.getIdentifier()).ifPresent(player -> {
            String title = options.getTitle();
            String subtitle = options.getSubtitle();

            Interpret.ifStringPresent(options.getChat(), message -> player.sendMessage(ChatColor.translateAlternateColorCodes('&',message)));
            Interpret.ifStringPresent(options.getActionbar(), message -> {}); /// TODO: 5/23/2021 implement actionbars

            player.sendTitle(
                    !title.equals(Interpret.NO_STRING) ? title : "",
                    !subtitle.equals(Interpret.NO_STRING) ? subtitle : "",
                    0,10,0);
        });

        continuable.fireNext();
    }

    @Override
    public Class<ChatStage> stageClass() {
        return ChatStage.class;
    }
}
