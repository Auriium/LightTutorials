package xyz.auriium.opentutorial.core.tutorial;

import java.util.Collection;
import java.util.Optional;

/**
 * control center for templates
 */
public interface TemplateController {

    /**
     * Gets a template by identifier
     * @param identifier identifier of the template as defined in the config
     * @return the template or null
     */
    Optional<Template> getByIdentifier(String identifier);

    Collection<String> getTemplateNames();

}
