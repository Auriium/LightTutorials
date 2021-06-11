package xyz.auriium.opentutorial.core.config.templates;

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
