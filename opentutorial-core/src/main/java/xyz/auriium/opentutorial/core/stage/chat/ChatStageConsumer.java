package xyz.auriium.opentutorial.core.stage.chat;

import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
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
            String title = options.getTitle();
            String subtitle = options.getSubtitle();

            Interpret.ifStringPresent(options.getChat(), player::sendMessage);
            Interpret.ifStringPresent(options.getActionbar(), player::sendActionbar);

            player.sendTitle(
                    !title.equals(Interpret.NO_STRING) ? title : "",
                    !subtitle.equals(Interpret.NO_STRING) ? subtitle : "",
                    0,10,10);
        });

        continuable.fireNext();
    }

    @Override
    public Class<ChatStage> stageClass() {
        return ChatStage.class;
    }

}
