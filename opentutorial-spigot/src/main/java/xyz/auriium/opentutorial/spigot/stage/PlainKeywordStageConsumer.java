package xyz.auriium.opentutorial.spigot.stage;

import xyz.auriium.opentutorial.core.event.chat.BaseChatEvent;
import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.core.config.ConfigHolder;
import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.platform.base.Scheduler;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.AbstractDelayConsumer;

/**
 * Handles response stages by checking if they contain a keyword
 */
public class PlainKeywordStageConsumer extends AbstractDelayConsumer<PlainKeywordStage, BaseChatEvent> {


    public PlainKeywordStageConsumer(Scheduler scheduler, TeachableRegistry registry, ConfigHolder<MessageConfig> config) {
        super(scheduler, registry, config);
    }

    @Override
    public Class<PlainKeywordStage> stageClass() {
        return PlainKeywordStage.class;
    }

    @Override
    public void consume(PlainKeywordStage stage, BaseChatEvent event, Tutorial tutorial) {
       String message = event.getMessage();

       for (String matchable : stage.getMatchables()) {
           if (message.toLowerCase().contains(matchable.toLowerCase())) {
               tutorial.fireNext();
               return;
           }
       }

       if (stage.isCancelOnFail()) {
           tutorial.fireCancel();
       }
    }

    @Override
    public Class<BaseChatEvent> eventClass() {
        return BaseChatEvent.class;
    }

}
