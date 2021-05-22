package me.aurium.opentutorial.stage.response;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.stage.await.AbstractAwaitConsumer;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Handles response stages by checking if they aaa eee ooo uuu cum
 */
public class ResponseStageConsumer extends AbstractAwaitConsumer<ResponseStage,DelegateChatEvent> {

    private final JavaPlugin plugin;

    //Java was a mistake
    private final Map<UUID, BukkitTask> mistakes = new HashMap<>();

    public ResponseStageConsumer(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Class<ResponseStage> stageClass() {
        return ResponseStage.class;
    }

    @Override
    public void started(ResponseStage options, Tutorial continuable) {
        super.started(options, continuable);

        if (options.getMaxDelay() != -1) {
            mistakes.put(continuable.getIdentifier(),plugin.getServer().getScheduler().runTaskLater(plugin, continuable::fireCancel, options.getMaxDelay()));
        }

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
