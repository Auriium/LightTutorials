package me.aurium.opentutorial.stage.response;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.await.AbstractDelayConsumer;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Handles response stages by checking if they aaa eee ooo uuu cum
 */
public class ResponseStageConsumer extends AbstractDelayConsumer<ResponseStage,DelegateChatEvent> {

    public ResponseStageConsumer(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public Class<ResponseStage> stageClass() {
        return ResponseStage.class;
    }

    @Override
    public void consume(ResponseStage stage, DelegateChatEvent event, Tutorial tutorial) {
       String message = event.getEvent().getMessage();

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
