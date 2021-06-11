package xyz.auriium.opentutorial.core.tutorial.template;

import xyz.auriium.opentutorial.core.config.ConfigHolder;
import xyz.auriium.opentutorial.core.config.templates.TemplateSection;
import xyz.auriium.opentutorial.core.config.templates.TutorialsConfig;

import java.util.Collection;
import java.util.Optional;

public class CommonTemplateController implements TemplateController {

    private final ConfigHolder<TutorialsConfig> config;

    public CommonTemplateController(ConfigHolder<TutorialsConfig> config) {
        this.config = config;
    }

    @Override
    public Optional<Template> getByIdentifier(String identifier) {
        TemplateSection section = config.get().getTemplates().get(identifier);

        if (section != null) {
            return Optional.of(new Template(section.getPermission(), section.getStages()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Collection<String> getTemplateNames() {
        return config.get().getTemplates().keySet();
    }

}
