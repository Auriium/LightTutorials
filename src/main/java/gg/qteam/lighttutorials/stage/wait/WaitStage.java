package gg.qteam.lighttutorials.stage.wait;

import gg.qteam.lighttutorials.AbstractStage;
import gg.qteam.lighttutorials.model.SequentialTutorial;

public class WaitStage extends AbstractStage {

    private final long delay;

    protected WaitStage(SequentialTutorial tutorial, long delay) {
        super(tutorial);
        this.delay = delay;
    }


    public long getDelay() {
        return delay;
    }
}
