package xyz.auriium.opentutorial.core.stage.age;

import xyz.auriium.opentutorial.core.event.chat.BaseChatEvent;
import xyz.auriium.opentutorial.core.platform.base.AudienceRegistry;
import xyz.auriium.opentutorial.core.config.ConfigHolder;
import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
import xyz.auriium.opentutorial.core.platform.base.Audience;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.AbstractDelayConsumer;

import java.util.Optional;

public class AgeStageConsumer extends AbstractDelayConsumer<AgeStage, BaseChatEvent> {

    public AgeStageConsumer(Scheduler scheduler, AudienceRegistry registry, ConfigHolder<MessageConfig> config) {
        super(scheduler, registry, config);
    }

    @Override
    public Class<AgeStage> stageClass() {
        return AgeStage.class;
    }

    @Override
    public void consume(AgeStage stage, BaseChatEvent event, Tutorial tutorial) {
        String message = event.getMessage().replaceAll("\\D+","");
        Optional<Audience> sender = registry.getAudienceByUUID(tutorial.getIdentifier());

        try {
            int age = Integer.parseInt(message);

            sender.ifPresent(player -> {
                if (age < stage.getBelowAge()) {
                    Interpret.ifStringPresent(stage.getRunOnFail(), cmd ->
                            player.runConsole(cmd.replaceAll("%PLAYER%",player.getName()))
                    );

                    if (stage.isCancelOnFail()) {
                        tutorial.fireCancel();
                    }
                }
            });

        } catch (NumberFormatException e) {
            sender.ifPresent(player -> config.get().notNumberMessage().send(player));
        }

        tutorial.fireNext();
    }

    @Override
    public Class<BaseChatEvent> eventClass() {
        return BaseChatEvent.class;
    }
}
