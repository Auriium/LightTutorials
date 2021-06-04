package xyz.auriium.opentutorial.core.tutorial.template;

import xyz.auriium.opentutorial.core.model.Cycleable;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

import java.util.Optional;

public interface TemplateController {

    Optional<Template> getByIdentifier(String identifier);



}
