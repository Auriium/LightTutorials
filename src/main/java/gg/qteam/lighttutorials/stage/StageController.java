package gg.qteam.lighttutorials.stage;

public interface StageController {

    /**
     * Gives the stage to an underlying stageserializer
     * @param stage the stage
     * @throws IllegalStateException if there is no underlizing serializer bound to the stage type
     */
    void accept(Stage stage);


}
