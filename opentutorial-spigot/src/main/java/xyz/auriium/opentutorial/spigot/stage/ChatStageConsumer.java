package xyz.auriium.opentutorial.spigot.stage;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.model.UserRegistry;
import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
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

            Interpret.ifStringPresent(options.getChat(), message -> player.sendMessage(color(message)));
            Interpret.ifStringPresent(options.getActionbar(), message -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color(message)))); /// TODO: 5/23/2021 implement actionbars

            player.sendTitle(
                    !title.equals(Interpret.NO_STRING) ? color(title) : "",
                    !subtitle.equals(Interpret.NO_STRING) ? color(subtitle) : "",
                    0,10,10);
        });

        continuable.fireNext();
    }

    @Override
    public Class<ChatStage> stageClass() {
        return ChatStage.class;
    }

    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&',string);
    }
}
