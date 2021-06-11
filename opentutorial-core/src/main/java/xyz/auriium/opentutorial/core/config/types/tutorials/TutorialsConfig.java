package xyz.auriium.opentutorial.core.config.types.tutorials;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;
import space.arim.dazzleconf.annote.SubSection;

import java.util.Map;

/**
 * File for all tutorials
 */
public interface TutorialsConfig {

    @ConfKey("tutorials")
    Map<String, @SubSection TemplateSection> getTemplates();

}
