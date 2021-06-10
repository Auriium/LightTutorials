package xyz.auriium.opentutorial.core.tutorial.template;

import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

import java.util.List;

/**
 * Represents a full template of tutorial stages loaded on initialization that can be cloned and used to create a new tutorial
 */
public class Template {

    private final String permission;
    private final List<Stage> stages;

    public Template(String permission, List<Stage> stages) {
        this.permission = permission;
        this.stages = stages;
    }


    public String getPermission() {
        return permission;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public boolean stageNotPresent(int point) {
        return point < stages.size();
    }
}
