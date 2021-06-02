package xyz.auriium.opentutorial.spigot.stage;

import xyz.auriium.opentutorial.core.model.Scheduler;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.AbstractDelayConsumer;

/**
 * Handles response stages by checking if they contain a keyword
 */
public class PlainKeywordStageConsumer extends AbstractDelayConsumer<PlainKeywordStage,DelegateChatEvent> {

    public PlainKeywordStageConsumer(Scheduler scheduler) {
        super(scheduler);
    }

    @Override
    public Class<PlainKeywordStage> stageClass() {
        return PlainKeywordStage.class;
    }

    @Override
    public void consume(PlainKeywordStage stage, DelegateChatEvent event, Tutorial tutorial) {
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
    public Class<DelegateChatEvent> eventClass() {
        return DelegateChatEvent.class;
    }

}
