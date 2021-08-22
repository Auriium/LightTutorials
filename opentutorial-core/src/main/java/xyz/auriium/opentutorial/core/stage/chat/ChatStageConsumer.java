package xyz.auriium.opentutorial.core.stage.chat;

import xyz.auriium.opentutorial.core.config.templates.util.Interpret;
import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.api.construct.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.BasicStageConsumer;

/**
 * Consumer system that handles {@link ChatStage}
 */
public class ChatStageConsumer implements BasicStageConsumer<ChatStage> {

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
