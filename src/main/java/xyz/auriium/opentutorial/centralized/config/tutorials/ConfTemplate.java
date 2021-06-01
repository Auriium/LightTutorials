package xyz.auriium.opentutorial.centralized.config.tutorials;

import space.arim.dazzleconf.annote.ConfComments;
import xyz.auriium.opentutorial.stage.Stage;
import space.arim.dazzleconf.annote.ConfKey;

import java.util.List;

public interface ConfTemplate {

    @ConfComments("The permission for this template. Can be 'none'. ")
    @ConfKey("permission")
    String getPermission();

    @ConfComments("The template for the config")
    @ConfKey("stages")
    List<Stage> getStages();

}
