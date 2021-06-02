package xyz.auriium.opentutorial.core.config.types.tutorials;

import space.arim.dazzleconf.annote.ConfKey;

import java.util.Map;

/**
 * File for all tutorials
 */
public interface TutorialsConfig {

    @ConfKey("tutorials")
    Map<String, TemplateSection> getTemplates();

}
