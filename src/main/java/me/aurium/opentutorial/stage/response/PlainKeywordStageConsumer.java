package me.aurium.opentutorial.stage.response;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.await.AbstractDelayConsumer;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Handles response stages by checking if they contain a keyword
 */
public class PlainKeywordStageConsumer extends AbstractDelayConsumer<PlainKeywordStage,DelegateChatEvent> {

    public PlainKeywordStageConsumer(JavaPlugin plugin) {
        super(plugin);
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
