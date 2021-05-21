package gg.qteam.lighttutorials;

import gg.qteam.lighttutorials.model.SequentialTutorial;
import gg.qteam.lighttutorials.stage.Stage;

public abstract class AbstractStage implements Stage {

    private final SequentialTutorial tutorial;

    protected AbstractStage(SequentialTutorial tutorial) {
        this.tutorial = tutorial;
    }

    @Override
    public SequentialTutorial getParent() {
        return tutorial;
    }
}
