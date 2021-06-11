package xyz.auriium.opentutorial.core.config.types.tutorials;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfKey;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

import java.util.List;

public interface TemplateSection {

    @ConfKey("permission")
    String getPermission();

    @ConfKey("stages")
    List<Stage> getStages();

}
