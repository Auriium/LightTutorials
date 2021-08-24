package xyz.auriium.opentutorial.core.tutorial.stage.keyword;

import xyz.auriium.opentutorial.core.config.messages.MessageConfig;
import xyz.auriium.opentutorial.core.event.chat.PlatformlessChatEvent;
import xyz.auriium.opentutorial.core.platform.Scheduler;
import xyz.auriium.opentutorial.core.platform.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.BasicDelayConsumer;

/**
 * Handles response stages by checking if they contain a keyword
 */
public class PlainKeywordStageConsumer extends BasicDelayConsumer<PlainKeywordStage, PlatformlessChatEvent> {


    public PlainKeywordStageConsumer(Scheduler scheduler, TeachableRegistry registry, MessageConfig config) {
        super(registry, scheduler, config);
    }

    @Override
    public Class<PlainKeywordStage> stageClass() {
        return PlainKeywordStage.class;
    }

    @Override
    public void consume(PlainKeywordStage stage, PlatformlessChatEvent event, Tutorial tutorial) {
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

       stage.getCommandOnFail().ifPresent(s -> {
           registry.getAudienceByUUID(tutorial.getIdentifier()).ifPresent(teachable -> {
               teachable.runConsole(s.replaceAll("%PLAYER%", teachable.getName()));
           });
       });
    }

    @Override
    public Class<PlatformlessChatEvent> eventClass() {
        return PlatformlessChatEvent.class;
    }

}
