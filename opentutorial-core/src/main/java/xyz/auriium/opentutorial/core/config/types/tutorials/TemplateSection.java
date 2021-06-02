package xyz.auriium.opentutorial.core.config.types.tutorials;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfKey;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

import java.util.List;

public interface TemplateSection {

    @ConfComments("The permission for this template. Can be 'none'. ")
    @ConfKey("permission")
    String getPermission();

    @ConfComments("The template for the config")
    @ConfKey("stages")
    List<Stage> getStages();

}
