package gg.qteam.lighttutorials.stage.listener;

import gg.qteam.lighttutorials.stage.await.AbstractAwaitConsumer;
import space.arim.omnibus.events.EventBus;

public class ResponseStageConsumer extends AbstractAwaitConsumer<ResponseStage,DelegateChatEvent> {

    @Override
    public Class<ResponseStage> stageClass() {
        return ResponseStage.class;
    }

    @Override
    public void consume(ResponseStage stage, DelegateChatEvent event) {

    }

    @Override
    public Class<DelegateChatEvent> eventClass() {
        return DelegateChatEvent.class;
    }
}
