package xyz.auriium.opentutorial.core.tutorial.template;

import xyz.auriium.opentutorial.core.model.Cycleable;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface TemplateController {

    Optional<Template> getByIdentifier(String identifier);

    Collection<String> getTemplateNames();

}
