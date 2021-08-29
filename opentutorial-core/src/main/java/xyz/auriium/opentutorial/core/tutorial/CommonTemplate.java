package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.template.Template;

import java.util.List;

public class CommonTemplate implements Template {

    private final String permission;
    private final List<Stage> stages;

    public CommonTemplate(String permission, List<Stage> stages) {
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
        return !stagePresent(point);
    }

    public boolean stagePresent(int point) {
        return point >= 0 && point < stages.size();
    }

}
