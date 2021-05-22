package me.aurium.opentutorial.centralized.ingame;

import me.aurium.opentutorial.stage.Stage;

import java.util.*;

/**
 * Represents a construction pile for a tutorial that is being built by a player in game
 */
public interface TemplateConstruction {

    Queue<Stage> getBackingDeque();

    void undo();
    void submit(Stage stage);

    String getName(); //finalized tutorial name

}
