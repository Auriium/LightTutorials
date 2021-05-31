package me.aurium.opentutorial.centralized.template;

import me.aurium.opentutorial.stage.Stage;

import java.util.List;

/**
 * Represents a full template of tutorial stages loaded on initialization that can be cloned and used to create a new tutorial
 */
public class Template {

    private final String identifier;
    private final List<Stage> stages;

    public Template(String identifier, List<Stage> stages) {
        this.identifier = identifier;
        this.stages = stages;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean hasStage(int i ) {
        return false;
    }
}
