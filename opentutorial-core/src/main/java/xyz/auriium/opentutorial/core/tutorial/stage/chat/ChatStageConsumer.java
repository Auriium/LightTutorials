package xyz.auriium.opentutorial.core.tutorial.stage.chat;

import xyz.auriium.opentutorial.core.platform.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

/**
 * Consumer system that handles {@link ChatStage}
 */
public class ChatStageConsumer implements xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer<ChatStage> {

    private final TeachableRegistry teachableRegistry;


    ChatStageConsumer(TeachableRegistry teachableRegistry) {
        this.teachableRegistry = teachableRegistry;
    }

    @Override
    public void started(ChatStage options, Tutorial continuable) {
        teachableRegistry.getAudienceByUUID(continuable.getIdentifier()).ifPresent(player -> {

            if (options.getTitle().isPresent() || options.getSubtitle().isPresent()) {
                player.sendTitle(options.getTitle().orElse(""),options.getSubtitle().orElse(""), 0, 20, 10);
            }

            options.getChat().ifPresent(player::sendMessage);
            options.getActionbar().ifPresent(player::sendActionbar);

        });

        continuable.fireNext();
    }

    @Override
    public Class<ChatStage> stageClass() {
        return ChatStage.class;
    }

}
