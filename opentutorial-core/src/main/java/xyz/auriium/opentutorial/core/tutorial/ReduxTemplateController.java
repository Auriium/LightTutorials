package xyz.auriium.opentutorial.core.tutorial;

import xyz.auriium.opentutorial.core.config.templates.TutorialsConfig;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ReduxTemplateController implements TemplateController {

    private final Map<String,Template> templates;

    public ReduxTemplateController(Map<String, Template> templates) {
        this.templates = templates;
    }

    @Override
    public Optional<Template> getByIdentifier(String identifier) {
        return Optional.ofNullable(templates.get(identifier));
    }

    @Override
    public Collection<String> getTemplateNames() {
        return templates.keySet();
    }

    @Override
    public Map<String,Template> getTemplates() {
        return templates;
    }

    public static TemplateController build(TutorialsConfig config) {

        Map<String,Template> map = new HashMap<>();

        config.getTemplates().forEach((string,section) -> {
            map.put(string,new CommonTemplate(section.getPermission(), section.getStages()));
        });

        return new ReduxTemplateController(map);
    }
}
