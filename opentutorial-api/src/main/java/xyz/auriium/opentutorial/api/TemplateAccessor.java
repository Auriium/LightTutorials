package xyz.auriium.opentutorial.api;

import xyz.auriium.opentutorial.api.construct.Template;

import java.util.Optional;

public interface TemplateAccessor {

    Optional<Template> getTemplate();

}
