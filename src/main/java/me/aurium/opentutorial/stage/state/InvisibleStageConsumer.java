package me.aurium.opentutorial.stage.state;

import me.aurium.opentutorial.centralized.Tutorial;
import me.aurium.opentutorial.centralized.states.StateMap;
import me.aurium.opentutorial.centralized.states.player.InvisibleKey;
import me.aurium.opentutorial.centralized.states.player.InvisibleState;
import me.aurium.opentutorial.stage.BasicStageConsumer;

public class InvisibleStageConsumer implements BasicStageConsumer<InvisibleStage> {

    private final StateMap map;

    public InvisibleStageConsumer(StateMap map) {
        this.map = map;
    }

    @Override
    public void started(InvisibleStage options, Tutorial continuable) {
        InvisibleState state = map.getState(continuable.getIdentifier(), InvisibleKey.INSTANCE);

        if (options.isOn()) {
            state.activate();
        } else {
            state.deactivate();
        }
    }

    @Override
    public Class<InvisibleStage> stageClass() {
        return InvisibleStage.class;
    }
}
