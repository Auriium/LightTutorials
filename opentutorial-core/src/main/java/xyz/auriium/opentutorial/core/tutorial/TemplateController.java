package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.opentutorial.core.tutorial.impl.CommonTemplateController;

import java.util.Collection;
import java.util.Optional;

public interface TemplateController {

    Optional<Template> getByIdentifier(String identifier);

    Collection<String> getTemplateNames();

}
