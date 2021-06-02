package xyz.auriium.opentutorial.core.tutorial.template;

import xyz.auriium.opentutorial.core.model.Cycleable;

import java.util.Optional;

public interface TemplateController {

    Optional<Template> getByIdentifier(String identifier);

}
