package xyz.auriium.opentutorial.core.types.chat;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.openmineplatform.api.interfaceable.user.PlatformTitle;
import xyz.auriium.openmineplatform.api.interfaceable.user.User;
import xyz.auriium.openmineplatform.api.interfaceable.user.UserTelescope;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.BasicConsumer;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.Map;

public class ChatStageHandler implements BasicConsumer<ChatStage> {
    @Override
    public String identifier() {
        return "chat";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        String chat = Interpret.getNullable(Identifiers.CHAT_CHAT, map, FlexibleType::getString);
        String actionbar = Interpret.getNullable(Identifiers.CHAT_ACTIONBAR, map, FlexibleType::getString);
        String title = Interpret.getNullable(Identifiers.CHAT_TITLE, map, FlexibleType::getString);
        String subtitle = Interpret.getNullable(Identifiers.CHAT_SUBTITLE, map, FlexibleType::getString);

        return new ChatStage(chat,actionbar,title,subtitle);

    }

    @Override
    public void stageStarted(ChatStage options, Tutorial tutorial) {
        User user = tutorial.getPlatform()
                .interRegistry()
                .getTelescoping(tutorial.getIdentifier(), UserTelescope.EXCEPTIONAL);

        if (options.getTitle().isPresent() || options.getSubtitle().isPresent()) {
            user.sendTitle(PlatformTitle.of(
                    options.getTitle().orElse(""),
                    options.getSubtitle().orElse(""),
                    0,
                    20,
                    10
            ));
        }

        options.getChat().ifPresent(user::sendString);
        options.getActionbar().ifPresent(user::sendActionbar);

        tutorial.fireNext();
    }

    @Override
    public Class<ChatStage> stageClass() {
        return ChatStage.class;
    }
}
