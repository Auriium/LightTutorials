package xyz.auriium.opentutorial.api.construct;

import xyz.auriium.opentutorial.api.construct.Stage;

import java.util.List;

/**
 * Represents a full template of tutorial stages loaded on initialization that can be cloned and used to create a new tutorial
 */
public interface Template {

    String getPermission();
    List<Stage> getStages();
    boolean stageNotPresent(int point);
    boolean stagePresent(int point);
}
