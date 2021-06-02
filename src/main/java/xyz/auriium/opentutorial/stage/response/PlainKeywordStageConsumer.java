package xyz.auriium.opentutorial.stage.response;

import xyz.auriium.opentutorial.PluginScheduler;
import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.stage.await.AbstractDelayConsumer;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Handles response stages by checking if they contain a keyword
 */
public class PlainKeywordStageConsumer extends AbstractDelayConsumer<PlainKeywordStage,DelegateChatEvent> {

    public PlainKeywordStageConsumer(PluginScheduler scheduler) {
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
