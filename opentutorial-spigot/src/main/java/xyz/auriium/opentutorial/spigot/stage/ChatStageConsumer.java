package xyz.auriium.opentutorial.spigot.stage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.UserRegistry;
import xyz.auriium.opentutorial.core.config.types.tutorials.Interpret;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.BasicStageConsumer;

public class ChatStageConsumer implements BasicStageConsumer<ChatStage> {

    private final UserRegistry<Player> registry;

    public ChatStageConsumer(UserRegistry<Player> registry) {
        this.registry = registry;
    }

    @Override
    public void started(ChatStage options, Tutorial continuable) {
        registry.getByUUID(continuable.getIdentifier()).ifPresent(player -> {
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
