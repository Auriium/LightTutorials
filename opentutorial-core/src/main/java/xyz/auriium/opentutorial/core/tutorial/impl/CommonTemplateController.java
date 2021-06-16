package xyz.auriium.opentutorial.core.tutorial.impl;

import xyz.auriium.opentutorial.core.config.templates.TemplateSection;
import xyz.auriium.opentutorial.core.config.templates.TutorialsConfig;
import xyz.auriium.opentutorial.core.tutorial.Template;
import xyz.auriium.opentutorial.core.tutorial.TemplateController;

import java.util.Collection;
import java.util.Optional;

public class CommonTemplateController implements TemplateController {

    private final TutorialsConfig config;

    public CommonTemplateController(TutorialsConfig config) {
        this.config = config;
    }

    @Override
    public Optional<Template> getByIdentifier(String identifier) {
        TemplateSection section = config.getTemplates().get(identifier);

        if (section != null) {
            return Optional.of(new Template(section.getPermission(), section.getStages()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Collection<String> getTemplateNames() {
        return config.getTemplates().keySet();
    }

}
