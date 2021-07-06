package xyz.auriium.opentutorial.core.stage.chat;

import xyz.auriium.opentutorial.api.construct.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.BasicStageConsumer;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.UUID;

public class SuppressStageConsumer implements StageConsumer<SuppressStage> {

    private final Suppressor suppressor;

    public SuppressStageConsumer(Suppressor suppressor) {
        this.suppressor = suppressor;
    }

    @Override
    public void started(SuppressStage options, Tutorial continuable) {
        suppressor.setSuppressed(continuable.getIdentifier(),options.isSuppressChat());

        continuable.fireNext();
    }

    @Override
    public Class<SuppressStage> stageClass() {
        return SuppressStage.class;
    }

    @Override
    public void closeSingle(UUID uuid) {
        suppressor.removeOne(uuid);
    }

    @Override
    public void close() {
        suppressor.removeAll();
    }
}
