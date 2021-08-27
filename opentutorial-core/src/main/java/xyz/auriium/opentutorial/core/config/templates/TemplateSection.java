package xyz.auriium.opentutorial.core.config.templates;

import space.arim.dazzleconf.annote.ConfKey;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;

import java.util.List;

public interface TemplateSection {

    @ConfKey("permission")
    String getPermission();

    @ConfKey("stages")
    List<Stage> getStages();

}
